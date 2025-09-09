package org.aemudapi.contribution.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionRequestDTO;
import org.aemudapi.contribution.dto.ContributionResponseDTO;
import org.aemudapi.contribution.dto.ContributionsPayementRequest;
import org.aemudapi.contribution.dto.ContributionsPayementResponse;
import org.aemudapi.contribution.entity.Contribution;
import org.aemudapi.contribution.entity.ContributionStatus;
import org.aemudapi.contribution.entity.Payement;
import org.aemudapi.contribution.mapper.ContributionMapper;
import org.aemudapi.contribution.mapper.PayementMapper;
import org.aemudapi.contribution.repository.ContributionRepository;
import org.aemudapi.contribution.repository.PayementRepository;
import org.aemudapi.contribution.service.ContributionService;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
public class ContributionServiceImpl implements ContributionService {
    private ContributionMapper contributionMapper;
    private ContributionRepository contributionRepository;
    private final MemberRepository memberRepository;
    private final PayementRepository payementRepository;
    private final PayementMapper payementMapper;
    private final SessionRepository sessionRepository;

    @Override
    public ResponseEntity<ResponseVO<ContributionResponseDTO>> addContribute(ContributionRequestDTO contributionRequestDTO) {
        Contribution contribution = this.contributionMapper.toEntity(contributionRequestDTO);
        contribution = this.contributionRepository.save(contribution);
        ContributionResponseDTO contributionResponseDTO = new ContributionResponseDTO(contribution.getId(), contribution.getSession().getId(), contribution.getMember().getId(), contribution.getMonth(), contribution.getAmountDue(), contribution.getAmountPaid(), contribution.getStatus());
        return new ResponseEntity<>(new ResponseVOBuilder<ContributionResponseDTO>().addData(contributionResponseDTO).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<ContributionResponseDTO>> modifyContribution(ContributionRequestDTO contributionDTO) {
        return this.addContribute(contributionDTO);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteContribution(String contributionDTO) {
        if (!this.contributionRepository.existsById(contributionDTO)) {
            contributionRepository.deleteById(contributionDTO);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityNotFoundException("entity not found");
    }

    @Override
    public ResponseEntity<ResponseVO<Integer>> countAllContributions() {
        int contributions = this.contributionRepository.countContributions();
        return new ResponseEntity<>(new ResponseVOBuilder<Integer>().addData(contributions).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Integer>> countContributionPeerSession(String sessionId) {
        int contributions = this.contributionRepository.countContributionBySessionId(sessionId);
        return new ResponseEntity<>(new ResponseVOBuilder<Integer>().addData(contributions).build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getContributionPeerMonth(String monthId, String sessionId) {
        List<Contribution> contributions = this.contributionRepository.findContributionByMonth(sessionId, monthId);
        List<ContributionResponseDTO> contributionDTOS = this.contributionMapper.toDTOList(contributions);
        return new ResponseEntity<>(new ResponseVOBuilder<List<ContributionResponseDTO>>().addData(contributionDTOS).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getMemberContributions(String memberId, String sessionId) {
        List<Contribution> contributions = this.contributionRepository.findMemberContributionsBySessionId(memberId, sessionId);
        List<ContributionResponseDTO> contributionDTOS = this.contributionMapper.toDTOList(contributions);
        return new ResponseEntity<>(new ResponseVOBuilder<List<ContributionResponseDTO>>().addData(contributionDTOS).build(), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<ContributionsPayementResponse>> payContributions(ContributionsPayementRequest payementRequest) {
        List<Contribution> contributions = this.contributionRepository.findAllById(payementRequest.contributionsID());
        Double totalPayement = getMemberAmountDue(contributions.get(0).getMember().getId(), contributions.size());
        Member member = this.memberRepository.findById(contributions.get(0).getMember().getId()).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        contributions.forEach(contribution -> {
            contribution.setStatus(ContributionStatus.PAID);
            contribution.setAmountPaid(member.getBourse().getMontant());
        });
        Payement payement = new Payement(contributions, totalPayement, LocalDate.now(), payementRequest.payementMethode());
        this.contributionRepository.saveAll(contributions);
        payement = this.payementRepository.save(payement);
        ContributionsPayementResponse contributionsPayementResponse = new ContributionsPayementResponse(this.payementMapper.toDto(payement), this.contributionMapper.toDTOList(contributions));
        return new ResponseEntity<>(new ResponseVOBuilder<ContributionsPayementResponse>().addData(contributionsPayementResponse).build(), HttpStatus.OK);
    }

    @Override
    public void createMemberCalendar(String memberId, String sessionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found"));

        YearMonth adhesionMonth = YearMonth.from(member.getMembershipInfo().getAdhesionDate());

        for (int month = 1; month <= 12; month++) {
            YearMonth current = YearMonth.of(session.getYear_(), month);

            boolean exists = contributionRepository
                    .existsByMemberAndSessionAndMonth(memberId, sessionId, current);

            if (!exists) {
                ContributionStatus status;

                if (current.isBefore(adhesionMonth)) {
                    status = ContributionStatus.NOT_APPLICABLE; // nouveau statut
                } else {
                    status = ContributionStatus.PENDING;
                }

                Contribution contribution = new Contribution(
                        member,
                        session,
                        current,
                        member.getBourse().getMontant(),
                        0.0,
                        status
                );

                contributionRepository.save(contribution);
            }
        }
    }

    @Override
    public ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getMemberContributionsCalendar(String memberId, String sessionId) {
        List<Contribution> contributions = this.contributionRepository.findMemberContributionsCalendarByMemberIdAndSessionId(memberId, sessionId);
        List<ContributionResponseDTO> contributionResponseDTOS = this.contributionMapper.toDTOList(contributions);
        return new ResponseEntity<>(new ResponseVOBuilder<List<ContributionResponseDTO>>().addData(contributionResponseDTOS).build(), HttpStatus.OK);
    }


    public Double getMemberAmountDue(String memberId, int months) {

        Member member = this.memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return months * member.getBourse().getMontant();
    }

//    @Override
//    public ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerMonth(String monthId, String sessionId) {
////        double contributionsAmount = this.contributionRepository.sumContributionsByMonth(sessionId, monthId);
////        return new ResponseEntity<>(new ResponseVOBuilder<Double>().addData(contributionsAmount).build(), HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerSessions(String sessionId) {
//        double contributionsAmount = this.contributionRepository.sumContributionsBySessionId(sessionId);
//        return new ResponseEntity<>(new ResponseVOBuilder<Double>().addData(contributionsAmount).build(), HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<ResponseVO<ContributionDTO>> contributeUsingNumPhone(String phoneNumber, String yearId, String monthId) {
//        Contribution contribution = this.contributionMapper.toEntity(phoneNumber, yearId, monthId);
//        List<Contribution> monthMemberContribution = this.contributionRepository.findMonthMemberByPhoneNumberContribution(yearId, monthId, phoneNumber);
//        if (!monthMemberContribution.isEmpty()) {
//            throw new EntityExistsException("Ce member à déjà cotiser pour ce mois");
//        }
//        ContributionDTO contributionDTO = this.contributionMapper.toDTO(this.contributionRepository.save(contribution));
//        return new ResponseEntity<>(new ResponseVOBuilder<ContributionDTO>().addData(contributionDTO).build(), HttpStatus.OK);
//    }

}
