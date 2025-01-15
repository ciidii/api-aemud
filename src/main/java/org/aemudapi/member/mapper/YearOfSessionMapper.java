package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.YearOfSessionResponse;
import org.aemudapi.member.entity.Session;
import org.springframework.stereotype.Component;


@Component
public class YearOfSessionMapper {

    // Mapper une entité Session vers un DTO YearOfSessionResponse
    public YearOfSessionResponse toDto(Session session) {
        if (session == null) {
            return null;
        }

        YearOfSessionResponse dto = new YearOfSessionResponse();
        dto.setId(session.getIdYear());
        dto.setYear_(session.getYear_());
        dto.setCurrentYear(session.isCurrentYear());

        return dto;
    }

    // Mapper un DTO YearOfSessionResponse vers une entité Session
    public Session toEntity(YearOfSessionResponse dto) {
        if (dto == null) {
            return null;
        }

        Session session = new Session();
        session.setIdYear(dto.getId());
        session.setYear_(dto.getYear_());
        session.setCurrentYear(dto.isCurrentYear());

        return session;
    }

}
