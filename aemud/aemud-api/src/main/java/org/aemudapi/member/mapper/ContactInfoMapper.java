package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.ContactInfoRequestDto;
import org.aemudapi.member.entity.ContactInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactInfoMapper {
    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;
    private final PersonToCallMapper personToCallMapper;

    public ContactInfo toEntity(ContactInfoRequestDto dto) {

        ContactInfo entity = new ContactInfo();
        entity.setEmail(dto.getEmail());
        entity.setNumberPhone(dto.getNumberPhone());
        entity.setPersonToCalls(personToCallMapper.toEntityList(dto.getPersonToCalls()));
        return entity;
    }

    public ContactInfoRequestDto toDTO(ContactInfo entity) {
        if (entity == null) {
            return null;
        }

        ContactInfoRequestDto dto = new ContactInfoRequestDto();

        dto.setEmail(entity.getEmail());
        dto.setNumberPhone(entity.getNumberPhone());
        dto.setPersonToCalls(this.personToCallMapper.toDtoList(entity.getPersonToCalls()));


        return dto;
    }

    private String getMemberID(String idMember) {
        Member member = this.memberRepository.findById(idMember)
                .orElseThrow(() -> new EntityNotFoundException("Pas de membre avec cette Identifiant " + idMember));
        return member.getId();
    }

    private String getIdYear(String idYear) {
        Session session = this.sessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant " + idYear));
        return session.getId();
    }
}
