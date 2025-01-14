package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.YearOfSessionResponse;
import org.aemudapi.member.entity.YearOfSession;
import org.springframework.stereotype.Component;


@Component
public class YearOfSessionMapper {

    // Mapper une entité YearOfSession vers un DTO YearOfSessionResponse
    public YearOfSessionResponse toDto(YearOfSession yearOfSession) {
        if (yearOfSession == null) {
            return null;
        }

        YearOfSessionResponse dto = new YearOfSessionResponse();
        dto.setId(yearOfSession.getIdYear());
        dto.setYear_(yearOfSession.getYear_());
        dto.setCurrentYear(yearOfSession.isCurrentYear());

        return dto;
    }

    // Mapper un DTO YearOfSessionResponse vers une entité YearOfSession
    public YearOfSession toEntity(YearOfSessionResponse dto) {
        if (dto == null) {
            return null;
        }

        YearOfSession yearOfSession = new YearOfSession();
        yearOfSession.setIdYear(dto.getId());
        yearOfSession.setYear_(dto.getYear_());
        yearOfSession.setCurrentYear(dto.isCurrentYear());

        return yearOfSession;
    }

}
