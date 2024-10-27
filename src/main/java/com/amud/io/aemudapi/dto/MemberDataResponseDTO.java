package com.amud.io.aemudapi.dto;

import lombok.Data;

@Data
public class MemberDataResponseDTO {
    MemberResponseDto member;
    AcademicInfoRequestDTO academicInfo;
    AddressInfoRequestDto addressInfo;
    ContactInfoRequestDto contactInfo;
}