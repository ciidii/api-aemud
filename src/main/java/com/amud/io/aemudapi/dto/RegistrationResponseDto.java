package com.amud.io.aemudapi.dto;

import com.amud.io.aemudapi.entities.YearOfSession;

public class RegistrationResponseDto {
    private MemberRequestDto memberRequestDto;
    private YearOfSession year;

    public MemberRequestDto getMemberDto() {
        return memberRequestDto;
    }

    public void setMemberDto(MemberRequestDto memberRequestDto) {
        this.memberRequestDto = memberRequestDto;
    }

    public YearOfSession getYear() {
        return year;
    }

    public void setYear(YearOfSession year) {
        this.year = year;
    }
}
