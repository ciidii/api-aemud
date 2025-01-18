package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class MemberRequestDto {
    private String id;
    private PersonalInfoDTO personalInfo;
    private MembershipInfoDTO membershipInfo;
    private ClubDto club;
    private CommissionDto commission;

    public MemberRequestDto() {
        //constructeur without params
    }
}
