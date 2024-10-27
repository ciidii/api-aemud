package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.PersonToCallDto;
import com.amud.io.aemudapi.entities.PersonToCall;
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
        entity.setLastname(dto.getLastname());
        entity.setFirstname(dto.getFirstname());
        entity.setRequiredNumberPhone(dto.getRequiredNumberPhone());
        entity.setOptionalNumberPhone(dto.getOptionalNumberPhone());
        entity.setRelationship(dto.getRelationship());

        return entity;
    }

    public PersonToCallDto toDto(PersonToCall entity) {
        if (entity == null) {
            return null;
        }

        PersonToCallDto dto = new PersonToCallDto();
        dto.setLastname(entity.getLastname());
        dto.setFirstname(entity.getFirstname());
        dto.setRequiredNumberPhone(entity.getRequiredNumberPhone());
        dto.setOptionalNumberPhone(entity.getOptionalNumberPhone());
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
