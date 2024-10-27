package com.amud.io.aemudapi.dto;

public class ReRegistrationKeyDto {
    Long yearOfRegistration;
    Long memberId;

    public Long getYearOfRegistration() {
        return yearOfRegistration;
    }

    public void setYearOfRegistration(Long yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
