package com.amud.io.aemudapi.dto;

import java.util.List;

public class RegistrationPearYearDto {
   private List<MemberRequestDto> registerMembers;
    private Long year;

    public List<MemberRequestDto> getRegisterMembers() {
        return registerMembers;
    }

    public void setRegisterMembers(List<MemberRequestDto> registerMembers) {
        this.registerMembers = registerMembers;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
