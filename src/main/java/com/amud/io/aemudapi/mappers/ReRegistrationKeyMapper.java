package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.ReRegistrationKeyDto;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import org.springframework.stereotype.Component;

@Component
public class ReRegistrationKeyMapper {

    public ReRegistrationKeyDto toDto(MemberAndYearKey memberAndYearKey) {
        if (memberAndYearKey == null) {
            return null;
        }

        ReRegistrationKeyDto dto = new ReRegistrationKeyDto();
        dto.setYearOfRegistration(memberAndYearKey.getYearOfRegistration());
        dto.setMemberId(memberAndYearKey.getMember());

        return dto;
    }

    public MemberAndYearKey toEntity(ReRegistrationKeyDto dto) {
        if (dto == null) {
            return null;
        }

        MemberAndYearKey memberAndYearKey = new MemberAndYearKey();
        memberAndYearKey.setYearOfRegistration(dto.getYearOfRegistration());
        memberAndYearKey.setMember(dto.getMemberId());

        return memberAndYearKey;
    }
}
