package org.aemudapi.member.mapper;

import lombok.RequiredArgsConstructor;
import org.aemudapi.member.dtos.CommissionDto;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.member.dtos.CommissionIdDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommissionMapper {
    private final MemberRepository memberRepository;

    public CommissionDto toDto(Commission commission) {
        CommissionDto dto = new CommissionDto();
        dto.setName(commission.getName());
        dto.setId(commission.getId());
        return dto;
    }

    public Commission toEntity(CommissionDto commissionDto) {
        Commission commission = new Commission();
        List<Member> members = this.memberRepository.findAllById(commissionDto.getMembers());
        commission.setMember(members);
        commission.setName(commissionDto.getName());
        commission.setId(commissionDto.getId());
        return commission;
    }

    public Commission toEntity(CommissionIdDto commissionDtoId) {
        Commission commission = new Commission();
        commission.setId(commissionDtoId.getId());
        return commission;
    }

    public List<CommissionDto> toDto(List<Commission> commission) {
        List<CommissionDto> dtos = new ArrayList<CommissionDto>();
        for (Commission commissionDto : commission) {
            dtos.add(toDto(commissionDto));
        }
        return dtos;
    }

    public List<Commission> toEntity(List<CommissionIdDto> dtos) {
        List<Commission> commissions = new ArrayList<>();
        for (CommissionIdDto dto : dtos) {
            commissions.add(toEntity(dto));
        }
        return commissions;
    }
}
