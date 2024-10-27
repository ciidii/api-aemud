package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.ContactInfoRequestDto;
import com.amud.io.aemudapi.entities.ContactInfo;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactInfoMapper {
    private final MemberRepository memberRepository;
    private final YearOfSessionRepository yearOfSessionRepository;
    private final PersonToCallMapper personToCallMapper;

    public ContactInfo toEntity(ContactInfoRequestDto dto) {
        MemberAndYearKey memberAndYearKey = new MemberAndYearKey();
        memberAndYearKey.setMember(getMemberID(dto.getMemberID()));
        memberAndYearKey.setYearOfRegistration(getIdYear(dto.getIdYear()));

        ContactInfo entity = new ContactInfo();
        entity.setEmail(dto.getEmail());
        entity.setNumberPhone(dto.getNumberPhone());
        entity.setPersonToCalls(this.personToCallMapper.toEntityList(dto.getPersonToCalls()));
        entity.setMemberAndYearKey(memberAndYearKey);
        return entity;
    }

    public ContactInfoRequestDto toDTO(ContactInfo entity) {
        ContactInfoRequestDto dto = new ContactInfoRequestDto();

        // Conversion des champs simples
        dto.setEmail(entity.getEmail());
        dto.setNumberPhone(entity.getNumberPhone());

        // Conversion de la clé composite MemberAndYearKey
        if (entity.getMemberAndYearKey() != null) {
            dto.setMemberID(entity.getMemberAndYearKey().getMember()); // Assuming getId() is the member ID method
            dto.setIdYear(entity.getMemberAndYearKey().getYearOfRegistration()); // Assuming getId() is the year ID method
        }

        // Conversion de la liste de personnes à contacter (PersonToCall)
        if (entity.getPersonToCalls() != null) {
            dto.setPersonToCalls(this.personToCallMapper.toDtoList(entity.getPersonToCalls()));
        }

        return dto;
    }

    private Long getMemberID(Long idMember) {
        Member member = this.memberRepository.findById(idMember)
                .orElseThrow(() -> new EntityNotFoundException("Pas de membre avec cette Identifiant " + idMember));
        return member.getId();
    }

    private Long getIdYear(Long idYear) {
        YearOfSession yearOfSession = this.yearOfSessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant " + idYear));
        return yearOfSession.getIdYear();
    }
}
