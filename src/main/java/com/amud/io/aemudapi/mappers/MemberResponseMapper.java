package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.MemberResponseDto;
import com.amud.io.aemudapi.entities.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberResponseMapper {

    public MemberResponseDto toDto(Member member) {
        if (member == null) {
            return null;
        }

        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(member.getId());
        dto.setPersonalInfo(member.getPersonalInfo());
        dto.setMembershipInfo(member.getMembershipInfo());
        return dto;
    }

    public List<MemberResponseDto> toDto(List<Member> members) {
        if (members == null) {
            return null;
        }

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(toDto(member));
        }

        return dtos;
    }
}

