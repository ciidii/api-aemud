package org.aemudapi.member.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String id;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;
    private AcademicInfoRequestDTO academicInfo;
    private AddressInfoRequestDto addressInfo;
    private ContactInfoRequestDto contactInfo;
    private BourseIdDTO bourse;
    private List<ClubIdDto> clubs;
    private List<CommissionIdDto> commissions;
}
