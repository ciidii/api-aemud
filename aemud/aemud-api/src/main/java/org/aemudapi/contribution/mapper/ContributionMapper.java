package org.aemudapi.contribution.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionRequestDTO;
import org.aemudapi.contribution.dto.ContributionResponseDTO;
import org.aemudapi.contribution.entity.Contribution;
import org.aemudapi.contribution.entity.Month;
import org.aemudapi.mandat.entity.Mandat;
import org.aemudapi.mandat.repository.MandatRepository;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ContributionMapper {
    private final MandatRepository mandatRepository;
    private final MemberRepository memberRepository;

    public ContributionResponseDTO toDTO(Contribution contribution) {
        if (contribution == null) {
            return null;
        }

        return new ContributionResponseDTO(contribution.getId(), contribution.getMandat().getId(), contribution.getMember().getId(), contribution.getMonth(), contribution.getAmountDue(), contribution.getAmountPaid(), contribution.getStatus());
    }


    public Contribution toEntity(ContributionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Contribution contribution = new Contribution();
        Mandat mandat = this.mandatRepository.findById(dto.mandatID()).orElseThrow(() -> new EntityNotFoundException("Mandat with id " + dto.mandatID() + " not found"));
        Member member = this.memberRepository.findById(dto.memberID()).orElseThrow(() -> new EntityNotFoundException("Member with id " + dto.memberID() + " not found"));
        contribution.setMember(member);
        contribution.setMonth(dto.month());
        contribution.setMandat(mandat);
        contribution.setAmountDue(member.getBourse().getMontant());
        contribution.setAmountPaid(dto.amountPaid());
        contribution.setStatus(dto.status());
        return contribution;
    }

    public Contribution toEntity(String memberPhoneNumber, String mandatId, String monthId) {
        Contribution contribution = new Contribution();
        Mandat mandat = this.mandatRepository.findById(mandatId).orElseThrow(() -> new EntityNotFoundException("Mandat with id " + mandatId + " not found"));
        Member member = this.memberRepository.findByNumberPhone(memberPhoneNumber).orElseThrow(() -> new EntityNotFoundException("Member with id " + memberPhoneNumber + " not found"));
        contribution.setMember(member);
//        contribution.setMonth(month);
        contribution.setMandat(mandat);
//        contribution.setAmount(member.getBourse().getMontant());

        return contribution;
    }

    public List<ContributionResponseDTO> toDTOList(List<Contribution> contributions) {
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