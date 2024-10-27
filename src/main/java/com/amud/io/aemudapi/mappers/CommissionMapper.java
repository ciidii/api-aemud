package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.CommissionDto;
import com.amud.io.aemudapi.entities.Commission;
import org.mapstruct.Mapper;
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
        return commission;
    }
}
