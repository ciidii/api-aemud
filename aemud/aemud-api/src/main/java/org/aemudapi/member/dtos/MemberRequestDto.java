package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String id;
    private String mandatId;
    private String phaseId;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;
    private AcademicInfoRequestDTO academicInfo;
    private AddressInfoRequestDto addressInfo;
    private ContactInfoRequestDto contactInfo;
    private String bourse;
    private List<String> clubs;
    private List<String> commissions;
    private ReligiousKnowledgeDto religiousKnowledge;
}
