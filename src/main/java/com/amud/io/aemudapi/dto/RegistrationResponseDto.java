package com.amud.io.aemudapi.dto;

import com.amud.io.aemudapi.entities.YearOfSession;
import lombok.Data;

@Data
public class RegistrationResponseDto {
    private MemberRequestDto memberRequestDto;
    private YearOfSession year;

    public MemberRequestDto getMemberDto() {
        return memberRequestDto;
    }

    public void setMemberDto(MemberRequestDto memberRequestDto) {
        this.memberRequestDto = memberRequestDto;
    }
}
