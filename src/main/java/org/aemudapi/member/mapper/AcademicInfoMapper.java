package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AcademicInfoRequestDTO;
import org.aemudapi.member.entity.MemberAndYearKey;
import org.aemudapi.member.entity.YearOfSession;
import org.aemudapi.member.entity.AcademicInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AcademicInfoMapper {
    private final MemberRepository memberRepository;
    private final YearOfSessionRepository yearOfSessionRepository;

    public AcademicInfo toEntity(AcademicInfoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        MemberAndYearKey memberAndYearKey = new MemberAndYearKey();
        memberAndYearKey.setMember(getMemberID(dto.getMemberID()));
        memberAndYearKey.setYearOfRegistration(getIdYear(dto.getIdYear()));

        AcademicInfo academicInfo = new AcademicInfo();
        academicInfo.setUniversity(dto.getUniversity());
        academicInfo.setFaculty(dto.getFaculty());
        academicInfo.setDepartment(dto.getDepartment());
        academicInfo.setSection(dto.getSection());
        academicInfo.setStudiesLevel(dto.getStudiesLevel());
        academicInfo.setMemberAndYearKey(memberAndYearKey);

        return academicInfo;
    }

    private Long getMemberID(Long idMember) {
        Member member = this.memberRepository.findById(idMember)
                .orElseThrow(() -> new EntityNotFoundException("Pas de membre avec cette Identifiant" + idMember));
        return member.getId();
    }

    private Long getIdYear(Long idYear) {
        YearOfSession yearOfSession = this.yearOfSessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant" + idYear));
        return yearOfSession.getIdYear();
    }

    public AcademicInfoRequestDTO toDto(AcademicInfo academicInfo) {
        if (academicInfo == null) {
            return null;
        }

        AcademicInfoRequestDTO dto = new AcademicInfoRequestDTO();
        dto.setUniversity(academicInfo.getUniversity());
        dto.setFaculty(academicInfo.getFaculty());
        dto.setDepartment(academicInfo.getDepartment());
        dto.setSection(academicInfo.getSection());
        dto.setStudiesLevel(academicInfo.getStudiesLevel());

        // Extraction des informations du MemberAndYearKey
        dto.setMemberID(academicInfo.getMemberAndYearKey().getMember());
        dto.setIdYear(academicInfo.getMemberAndYearKey().getYearOfRegistration());

        return dto;
    }

}
