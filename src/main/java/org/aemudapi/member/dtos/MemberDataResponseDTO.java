package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDataResponseDTO {
    private String id;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;
    private AcademicInfoRequestDTO academicInfo;
    private AddressInfoRequestDto addressInfo;
    private ContactInfoRequestDto contactInfo;
    private BourseDTO bourse;
    private List<ClubDto> clubs;
    private List<CommissionDto> commissions;
}
