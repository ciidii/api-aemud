package com.amud.io.aemudapi.dto;

import lombok.Data;

@Data
public class MemberDataRequestDTO {
    MemberRequestDto member;
    AcademicInfoRequestDTO academicInfo;
    AddressInfoRequestDto addressInfo;
    ContactInfoRequestDto contactInfo;
}
