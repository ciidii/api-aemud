package org.aemudapi.contribution.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionDTO;
import org.aemudapi.contribution.entity.Contribution;
import org.aemudapi.contribution.mapper.ContributionMapper;
import org.aemudapi.contribution.repository.ContributionRepository;
import org.aemudapi.contribution.service.ContributionService;
import org.aemudapi.member.service.MemberService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContributionServiceImpl implements ContributionService {
    private ContributionMapper contributionMapper;
    private ContributionRepository contributionRepository;
    private final MemberService memberService;

    @Override
    public ResponseEntity<ResponseVO<ContributionDTO>> contribute(ContributionDTO contributionDTO) {
        Contribution contribution = this.contributionMapper.toEntity(contributionDTO);
        this.contributionRepository.save(contribution);
        return new ResponseEntity<>(new ResponseVOBuilder<ContributionDTO>().addData(contributionDTO).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<ContributionDTO>> modifyContribution(ContributionDTO contributionDTO) {
        return this.contribute(contributionDTO);
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
    public ResponseEntity<ResponseVO<List<ContributionDTO>>> getContributionPeerMonth(String monthId, String sessionId) {
        List<Contribution> contributions = this.contributionRepository.findContributionByMonth(sessionId, monthId);
        List<ContributionDTO> contributionDTOS = this.contributionMapper.toDTOList(contributions);
        return new ResponseEntity<>(new ResponseVOBuilder<List<ContributionDTO>>().addData(contributionDTOS).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<ContributionDTO>>> getMemberContributions(String memberId, String sessionId) {
        List<Contribution> contributions = this.contributionRepository.findMemberContributionsBySessionId(memberId, sessionId);
        List<ContributionDTO> contributionDTOS = this.contributionMapper.toDTOList(contributions);
        return new ResponseEntity<>(new ResponseVOBuilder<List<ContributionDTO>>().addData(contributionDTOS).build(), HttpStatus.OK);
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
