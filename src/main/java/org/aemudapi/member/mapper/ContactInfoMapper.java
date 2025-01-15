package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.ContactInfoRequestDto;
import org.aemudapi.member.entity.Member_Session_PK;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.entity.ContactInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactInfoMapper {
    private final MemberRepository memberRepository;
    private final YearOfSessionRepository yearOfSessionRepository;
    private final PersonToCallMapper personToCallMapper;

    public ContactInfo toEntity(ContactInfoRequestDto dto) {
        Member_Session_PK memberSessionPK = new Member_Session_PK();
        memberSessionPK.setMember(getMemberID(dto.getMemberID()));
        memberSessionPK.setYearOfRegistration(getIdYear(dto.getIdYear()));

        ContactInfo entity = new ContactInfo();
        entity.setEmail(dto.getEmail());
        entity.setNumberPhone(dto.getNumberPhone());
        entity.setPersonToCalls(this.personToCallMapper.toEntityList(dto.getPersonToCalls()));
        entity.setMemberSessionPK(memberSessionPK);
        return entity;
    }

    public ContactInfoRequestDto toDTO(ContactInfo entity) {
        ContactInfoRequestDto dto = new ContactInfoRequestDto();

        // Conversion des champs simples
        dto.setEmail(entity.getEmail());
        dto.setNumberPhone(entity.getNumberPhone());

        // Conversion de la clé composite Member_Session_PK
        if (entity.getMemberSessionPK() != null) {
            dto.setMemberID(entity.getMemberSessionPK().getMember()); // Assuming getId() is the member ID method
            dto.setIdYear(entity.getMemberSessionPK().getYearOfRegistration()); // Assuming getId() is the year ID method
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
        Session session = this.yearOfSessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant " + idYear));
        return session.getIdYear();
    }
}
