package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.PersonalInfoDTO;
import com.amud.io.aemudapi.entities.PersonalInfo;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoMapper {
    public PersonalInfo toEntity(PersonalInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        PersonalInfo entity = new PersonalInfo();
        entity.setName(dto.getName());
        entity.setFirstname(dto.getFirstname());
        entity.setNationality(dto.getNationality());
        entity.setBirthday(dto.getBirthday());
        entity.setMaritalStatus(dto.getMaritalStatus());

        return entity;
    }

    public PersonalInfoDTO toDto(PersonalInfo entity) {
        if (entity == null) {
            return null;
        }

        PersonalInfoDTO dto = new PersonalInfoDTO();
        dto.setName(entity.getName());
        dto.setFirstname(entity.getFirstname());
        dto.setNationality(entity.getNationality());
        dto.setBirthday(entity.getBirthday());
        dto.setMaritalStatus(entity.getMaritalStatus());

        return dto;
    }
}
