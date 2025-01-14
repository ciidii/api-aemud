package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class MemberDataResponseDTO {
    MemberResponseDto member;
    AcademicInfoRequestDTO academicInfo;
    AddressInfoRequestDto addressInfo;
    ContactInfoRequestDto contactInfo;
}
