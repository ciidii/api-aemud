package com.amud.io.aemudapi.dto;

import com.amud.io.aemudapi.entities.*;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.util.List;

@Data
public class MemberResponseDto {
    private Long id;
    private PersonalInfo personalInfo;
    private MembershipInfo membershipInfo;

    public MemberResponseDto() {
        //constructeur without params
    }
}