package com.amud.io.aemudapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationPearYearDto {
    private List<MemberRequestDto> registerMembers;
    private Long year;
}
