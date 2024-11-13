package com.amud.io.aemudapi.dto;

import lombok.Data;

@Data
public class ReRegistrationDto {
    private ReRegistrationKeyDto reRegistrationKeyDto;
    private MemberHasBourseKeyDto memberHasBourseKeyDto;

    public ReRegistrationDto() {
        //Constructor Without args
    }
}
