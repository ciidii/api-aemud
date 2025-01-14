package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class MemberDataRequestDTO {
    MemberRequestDto member;
    AcademicInfoRequestDTO academicInfo;
    AddressInfoRequestDto addressInfo;
    ContactInfoRequestDto contactInfo;
}
