package org.aemudapi.member.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MemberDataResponseDTO {
    private String id;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;
    private AcademicInfoRequestDTO academicInfoRequest;
    private AddressInfoRequestDto addressInfo;
    private ContactInfoRequestDto contactInfo;
    private BourseDTO bourse;
    private List<ClubDto> clubs;
    private List<CommissionDto> commissions;
}
