package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.mandat.entity.Mandat;
import org.aemudapi.mandat.repository.MandatRepository;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestWithPhoneNumberDto;
import org.aemudapi.member.dtos.RegistrationResponseDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RegistrationMapper {
    private final MemberRepository memberRepository;
    private final MandatRepository mandatRepository;

    public Registration toEntity(RegistrationRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Registration registration = new Registration();
        Member member = this.getMember(dto.getMember());
        Mandat mandat = this.getMandat(dto.getMandatId());
        registration.setId(mandat.getId());
        registration.setMember(member);
        registration.setMandat(mandat);
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
        Mandat mandat = this.getMandat(dto.getMandatId());
        registration.setId(dto.getId());
        registration.setMember(member);
        registration.setMandat(mandat);
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
        dto.setMandat(registration.getMandat().getNom());
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

    Mandat getMandat(String mandatId) {
        return this.mandatRepository.findById(mandatId).orElseThrow(() -> new EntityNotFoundException("Mandat not found with id " + mandatId));
    }

}
