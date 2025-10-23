package org.aemudapi.member.mapper;

import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.MembershipInfoDTO;
import org.aemudapi.member.entity.MembershipInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MembershipInfoMapper {

    public MembershipInfo toEntity(MembershipInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        MembershipInfo entity = new MembershipInfo();
        entity.setLegacyInstitution(dto.getLegacyInstitution());
        entity.setYearOfBac(dto.getYearOfBac());
        entity.setBacMention(dto.getBacMention());
        entity.setBacSeries(dto.getBacSeries());
        entity.setAemudCourses(dto.getAemudCourses());
        entity.setOtherCourses(dto.getOtherCourses());
        entity.setParticipatedActivity(dto.getParticipatedActivity());
        entity.setPoliticOrganisation(dto.getPoliticOrganisation());
        return entity;
    }

    // Méthode pour mapper MembershipInfo vers MembershipInfoDTO
    public MembershipInfoDTO toDto(MembershipInfo entity) {
        if (entity == null) {
            return null;
        }

        MembershipInfoDTO dto = new MembershipInfoDTO();
        dto.setAemudCourses(entity.getAemudCourses());
        dto.setOtherCourses(entity.getOtherCourses());
        dto.setBacSeries(entity.getBacSeries());
        dto.setBacMention(entity.getBacMention());
        dto.setYearOfBac(entity.getYearOfBac());
        dto.setParticipatedActivity(entity.getParticipatedActivity());
        dto.setPoliticOrganisation(entity.getPoliticOrganisation());
        dto.setLegacyInstitution(entity.getLegacyInstitution());
        entity.setYearOfBac(entity.getYearOfBac());
        entity.setBacMention(entity.getBacMention());
        entity.setBacSeries(entity.getBacSeries());
        return dto;
    }

    public List<MembershipInfo> toEntity(List<MembershipInfoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<MembershipInfo> entities = new ArrayList<>();
        dtos.forEach((entitie) -> {
            entities.add(toEntity(entitie));
        });
        return entities;
    }
}
