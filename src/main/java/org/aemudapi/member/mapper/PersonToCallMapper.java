package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.PersonToCallDto;
import org.aemudapi.member.entity.PersonToCall;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonToCallMapper {

    public PersonToCall toEntity(PersonToCallDto dto) {
        if (dto == null) {
            return null;
        }

        PersonToCall entity = new PersonToCall();
        entity.setPersonToCallName(dto.getLastname());
        entity.setPersonToCallFirstname(dto.getFirstname());
        entity.setPersonToCallRequiredNumberPhone(dto.getRequiredNumberPhone());
        entity.setPersonToCallOptionalNumberPhone(dto.getOptionalNumberPhone());
        entity.setRelationship(dto.getRelationship());

        return entity;
    }

    public PersonToCallDto toDto(PersonToCall entity) {
        if (entity == null) {
            return null;
        }

        PersonToCallDto dto = new PersonToCallDto();
        dto.setLastname(entity.getPersonToCallName());
        dto.setFirstname(entity.getPersonToCallFirstname());
        dto.setRequiredNumberPhone(entity.getPersonToCallRequiredNumberPhone());
        dto.setOptionalNumberPhone(entity.getPersonToCallOptionalNumberPhone());
        dto.setRelationship(entity.getRelationship());

        return dto;
    }

    public List<PersonToCall> toEntityList(List<PersonToCallDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<PersonToCallDto> toDtoList(List<PersonToCall> entityList) {
        if (entityList == null) {
            return null;
        }

        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
