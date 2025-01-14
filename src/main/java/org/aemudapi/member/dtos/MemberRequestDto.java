package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class MemberRequestDto {
    private Long id;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;

    public MemberRequestDto() {
        //constructeur without params
    }
}
