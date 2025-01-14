package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.MemberHasBourseKeyDto;
import org.aemudapi.member.entity.MemberHasBourseKey;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberHasBourseKeyMapper {

    // Méthode pour mapper une entité vers un DTO
    public MemberHasBourseKeyDto toDto(MemberHasBourseKey entity) {
        if (entity == null) {
            return null;
        }

        MemberHasBourseKeyDto dto = new MemberHasBourseKeyDto();
        dto.setYear_(entity.getYear_());
        dto.setMemberId(entity.getMemberId());
        dto.setBourseId(entity.getBourseId());

        return dto;
    }

    // Méthode pour mapper une liste d'entités vers une liste de DTO
    public List<MemberHasBourseKeyDto> toDtoList(List<MemberHasBourseKey> entities) {
        if (entities == null) {
            return null;
        }

        List<MemberHasBourseKeyDto> list = new ArrayList<>();
        for (MemberHasBourseKey entity : entities) {
            MemberHasBourseKeyDto dto = toDto(entity);
            list.add(dto);
        }
        return list;
    }

    // Méthode pour mapper un DTO vers une entité
    public MemberHasBourseKey toEntity(MemberHasBourseKeyDto dto) {
        if (dto == null) {
            return null;
        }

        MemberHasBourseKey entity = new MemberHasBourseKey();
        entity.setYear_(dto.getYear_());
        entity.setMemberId(dto.getMemberId());
        entity.setBourseId(dto.getBourseId());

        return entity;
    }

    // Méthode pour mapper une liste de DTO vers une liste d'entités
    public List<MemberHasBourseKey> toEntityList(List<MemberHasBourseKeyDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
