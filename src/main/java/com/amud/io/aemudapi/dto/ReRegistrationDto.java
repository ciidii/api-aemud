package com.amud.io.aemudapi.dto;

public class ReRegistrationDto {
    private ReRegistrationKeyDto reRegistrationKeyDto;
    private MemberHasBourseKeyDto memberHasBourseKeyDto;
    public ReRegistrationDto() {
        //Constructor Without args
    }

    public ReRegistrationKeyDto getReRegistrationKeyDto() {
        return reRegistrationKeyDto;
    }

    public void setReRegistrationKeyDto(ReRegistrationKeyDto reRegistrationKeyDto) {
        this.reRegistrationKeyDto = reRegistrationKeyDto;
    }

    public MemberHasBourseKeyDto getMemberHasBourseKeyDto() {
        return memberHasBourseKeyDto;
    }

    public void setMemberHasBourseKeyDto(MemberHasBourseKeyDto memberHasBourseKeyDto) {
        this.memberHasBourseKeyDto = memberHasBourseKeyDto;
    }
}
