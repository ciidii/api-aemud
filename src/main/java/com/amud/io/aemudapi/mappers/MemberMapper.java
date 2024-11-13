package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.MemberDataResponseDTO;
import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.entities.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MemberMapper {
    private final PersonalInfoMapper personalInfoMapper;
    private final MembershipInfoMapper membershipInfo;
    private final MemberResponseMapper memberResponseMapper;


    public MemberRequestDto toDto(Member member) {
        if (member == null) {
            return null;
        }
        MemberRequestDto dto = new MemberRequestDto();
        dto.setId(member.getId());
        dto.setPersonalInfo(this.personalInfoMapper.toDto(member.getPersonalInfo()));
        return dto;
    }

    public MemberDataResponseDTO memberDataDto(Member member) {
        if (member == null) {
            return null;
        }
        MemberDataResponseDTO dto = new MemberDataResponseDTO();
        dto.setMember(this.memberResponseMapper.toDto(member));
        return dto;
    }

    public Member toEntity(MemberRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Member entity = new Member();
        entity.setPersonalInfo(this.personalInfoMapper.toEntity(dto.getPersonalInfo()));
        entity.setMembershipInfo(this.membershipInfo.toEntity(dto.getMembershipInfo()));
        return entity;
    }

    public List<MemberRequestDto> toDto(List<Member> members) {
        if (members == null) {
            return null;
        }

        List<MemberRequestDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(toDto(member));
        }

        return dtos;
    }

    public List<Member> toEntity(List<MemberRequestDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Member> members = new ArrayList<>();
        for (MemberRequestDto dto : dtos) {
            members.add(toEntity(dto));
        }

        return members;
    }


}

