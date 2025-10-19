package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestWithPhoneNumberDto;
import org.aemudapi.member.dtos.RegistrationResponseDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RegistrationMapper {
    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;

    public Registration toEntity(RegistrationRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Registration registration = new Registration();
        Member member = this.getMember(dto.getMember());
        Session session = this.getSession(dto.getSessionId());
        registration.setId(session.getId());
        registration.setMember(member);
        registration.setSession(session);
        registration.setRegistrationType(dto.getRegistrationType());
        registration.setStatusPayment(dto.isStatusPayment());
        registration.setRegistrationStatus(dto.getRegistrationStatus());

        // dateInscription est automatiquement initialisée dans l'entité, mais on peut l'ajuster si besoin.
        return registration;
    }

    public Registration toEntity(RegistrationRequestWithPhoneNumberDto dto, Member member) {
        if (dto == null) {
            return null;
        }

        Registration registration = new Registration();
        Session session = this.getSession(dto.getSession());
        registration.setId(dto.getId());
        registration.setMember(member);
        registration.setSession(session);
        registration.setRegistrationType(dto.getRegistrationType());
        registration.setStatusPayment(dto.isStatusPayment());
        registration.setRegistrationStatus(dto.getRegistrationStatus());

        // dateInscription est automatiquement initialisée dans l'entité, mais on peut l'ajuster si besoin.
        return registration;
    }

    public RegistrationResponseDto toDto(Registration registration) {
        if (registration == null) {
            return null;
        }

        RegistrationResponseDto dto = new RegistrationResponseDto();
        dto.setId(registration.getId());
        dto.setMember(registration.getMember().getId());
        dto.setSession(registration.getSession().getYear_());
        dto.setRegistrationType(registration.getRegistrationType());
        dto.setStatusPayment(registration.isStatusPayment());
        dto.setDateInscription(registration.getDateInscription());
        dto.setRegistrationStatus(registration.getRegistrationStatus());

        return dto;
    }

    public List<Registration> toEntity(List<RegistrationRequestDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return new ArrayList<>();
        }

        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<RegistrationResponseDto> toDto(List<Registration> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return new ArrayList<>();
        }

        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    Member getMember(String memberId) {
        return this.memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found with id " + memberId));
    }

    Session getSession(String sessionId) {
        return this.sessionRepository.findById(sessionId).orElseThrow(() -> new EntityNotFoundException("Session not found with id " + sessionId));
    }

}
