package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AcademicInfoRequestDTO;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.entity.AcademicInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AcademicInfoMapper {
    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;

    public AcademicInfo toEntity(AcademicInfoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        AcademicInfo academicInfo = new AcademicInfo();
        academicInfo.setStudiesLevel(dto.getStudiesLevel());
        academicInfo.setStudiesDomain(dto.getStudiesDomain());
        academicInfo.setInstitutionName(dto.getInstitutionName());

        return academicInfo;
    }

    private String getMemberID(String idMember) {
        Member member = this.memberRepository.findById(idMember)
                .orElseThrow(() -> new EntityNotFoundException("Pas de membre avec cette Identifiant" + idMember));
        return member.getId();
    }

    private String getIdYear(String idYear) {
        Session session = this.sessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant" + idYear));
        return session.getId();
    }

    public AcademicInfoRequestDTO toDto(AcademicInfo academicInfo) {
        if (academicInfo == null) {
            return null;
        }

        AcademicInfoRequestDTO dto = new AcademicInfoRequestDTO();
        dto.setStudiesLevel(academicInfo.getStudiesLevel());
        dto.setStudiesDomain(academicInfo.getStudiesDomain());
        dto.setInstitutionName(academicInfo.getInstitutionName());

        return dto;
    }

}
