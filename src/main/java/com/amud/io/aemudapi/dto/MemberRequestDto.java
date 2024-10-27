package com.amud.io.aemudapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberRequestDto {
    private Long id;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;

    public MemberRequestDto() {
        //constructeur without params
    }
}
