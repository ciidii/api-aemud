package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.CommissionDto;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.member.dtos.CommissionIdDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommissionMapper {
    public CommissionDto toDto(Commission commission) {
        CommissionDto dto = new CommissionDto();
        dto.setName(commission.getName());
        dto.setId(commission.getId());
        return dto;
    }

    public Commission toEntity(CommissionDto commissionDto) {
        Commission commission = new Commission();
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
