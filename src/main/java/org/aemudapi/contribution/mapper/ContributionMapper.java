package org.aemudapi.contribution.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionDTO;
import org.aemudapi.contribution.dto.ContributionRequestDTO;
import org.aemudapi.contribution.dto.ContributionResponseDTO;
import org.aemudapi.contribution.entity.Contribution;
import org.aemudapi.contribution.entity.Month;
import org.aemudapi.contribution.repository.MonthRepository;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.aemudapi.member.service.MemberService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ContributionMapper {
    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;
    private final MonthRepository monthRepository;

    public ContributionDTO toDTO(Contribution contribution) {
        if (contribution == null) {
            return null;
        }

        ContributionDTO dto = new ContributionDTO();
        dto.setContributionId(contribution.getId() != null ? contribution.getId() : null);
        dto.setMemberId(contribution.getMember() != null ? contribution.getMember().getId() : null);
        dto.setSessionId(contribution.getSession() != null ? contribution.getSession().getId() : null);
//        dto.setMonthId(contribution.getMonth() != null ? contribution.getMonth().getId() : null);
//        dto.setAmount(contribution.getAmount());
//        dto.setPaymentDate(contribution.getPaymentDate());

        return dto;
    }


    public Contribution toEntity(ContributionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Contribution contribution = new Contribution();
        Session session = this.sessionRepository.findById(dto.sessionID()).orElseThrow(() -> new EntityNotFoundException("Session with id " + dto.sessionID() + " not found"));
        Member member = this.memberRepository.findById(dto.memberID()).orElseThrow(() -> new EntityNotFoundException("Member with id " + dto.memberID() + " not found"));
        contribution.setMember(member);
        contribution.setMonth(dto.month());
        contribution.setSession(session);
        contribution.setAmountDue(member.getBourse().getMontant());
        contribution.setAmountPaid(dto.amountPaid());
        contribution.setStatus(dto.status());
        return contribution;
    }

    public Contribution toEntity(String memberPhoneNumber, String sessionId, String monthId) {
        Contribution contribution = new Contribution();
        Session session = this.sessionRepository.findById(sessionId).orElseThrow(() -> new EntityNotFoundException("Session with id " + sessionId + " not found"));
        Member member = this.memberRepository.findByNumberPhone(memberPhoneNumber).orElseThrow(() -> new EntityNotFoundException("Member with id " + memberPhoneNumber + " not found"));
        Month month = this.monthRepository.findById(monthId).orElseThrow(() -> new EntityNotFoundException("Member with id " + monthId + " not found"));
        contribution.setMember(member);
//        contribution.setMonth(month);
        contribution.setSession(session);
//        contribution.setAmount(member.getBourse().getMontant());

        return contribution;
    }

    public List<ContributionDTO> toDTOList(List<Contribution> contributions) {
        if (contributions == null || contributions.isEmpty()) {
            return List.of();
        }

        return contributions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Contribution> toEntityList(List<ContributionRequestDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}