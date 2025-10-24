package org.aemudapi.member.dtos;

import lombok.*;

import java.util.List;
import java.util.UUID;

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
