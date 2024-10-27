package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.ContributionKeyDto;
import com.amud.io.aemudapi.entities.ContributionKey;
import org.springframework.stereotype.Component;

@Component
public class ContributionKeyMapper {

    public ContributionKey toEntity(ContributionKeyDto dto) {
        if (dto == null) {
            return null;
        }

        ContributionKey entity = new ContributionKey();
        entity.setIdMonth(dto.getIdMonth());
        entity.setIdYear(dto.getIdYear());
        entity.setMemberId(dto.getMemberId());

        return entity;
    }

    public ContributionKeyDto toDto(ContributionKey entity) {
        if (entity == null) {
            return null;
        }

        ContributionKeyDto dto = new ContributionKeyDto();
        dto.setIdMonth(entity.getIdMonth());
        dto.setIdYear(entity.getIdYear());
        dto.setMemberId(entity.getMemberId());

        return dto;
    }
}
