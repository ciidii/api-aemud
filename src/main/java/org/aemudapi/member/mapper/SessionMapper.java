package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.SessionRequestDTO;
import org.aemudapi.member.dtos.SessionResponseDTO;
import org.aemudapi.member.entity.Session;
import org.springframework.stereotype.Component;


@Component
public class SessionMapper {

    // Mapper une entité SessionService vers un DTO SessionResponseDTO
    public SessionResponseDTO toDto(Session session) {
        if (session == null) {
            return null;
        }

        SessionResponseDTO dto = new SessionResponseDTO();
        dto.setId(session.getId());
        dto.setSession(session.getYear_());
        dto.setCurrentYear(session.isCurrent());

        return dto;
    }

    // Mapper un DTO SessionResponseDTO vers une entité SessionService
    public Session toEntity(SessionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Session session = new Session();
        session.setId(dto.getId());
        session.setYear_(dto.getSession());
        session.setCurrent(dto.isCurrentYear());

        return session;
    }

}
