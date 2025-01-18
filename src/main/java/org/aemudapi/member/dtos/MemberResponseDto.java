package org.aemudapi.member.dtos;

import lombok.Data;
import org.aemudapi.member.entity.MembershipInfo;
import org.aemudapi.member.entity.PersonalInfo;

@Data
public class MemberResponseDto {
    private String id;
    private PersonalInfo personalInfo;
    private MembershipInfo membershipInfo;

    public MemberResponseDto() {
        //constructeur without params
    }
}