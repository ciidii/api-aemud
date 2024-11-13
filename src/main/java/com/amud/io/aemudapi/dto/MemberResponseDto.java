package com.amud.io.aemudapi.dto;

import com.amud.io.aemudapi.entities.MembershipInfo;
import com.amud.io.aemudapi.entities.PersonalInfo;
import lombok.Data;

@Data
public class MemberResponseDto {
    private Long id;
    private PersonalInfo personalInfo;
    private MembershipInfo membershipInfo;

    public MemberResponseDto() {
        //constructeur without params
    }
}