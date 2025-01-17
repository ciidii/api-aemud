package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.CommissionDto;
import org.aemudapi.commission.entity.Commission;
import org.springframework.stereotype.Component;

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
}
