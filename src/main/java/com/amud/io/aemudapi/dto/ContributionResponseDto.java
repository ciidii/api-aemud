package com.amud.io.aemudapi.dto;

import com.google.api.client.util.DateTime;

import java.time.LocalDateTime;

public class ContributionResponseDto {
    private String month;

    private Long idYear;

    private String memberId;

    private LocalDateTime dateTime;

    private Double Amount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getIdYear() {
        return idYear;
    }

    public void setIdYear(Long idYear) {
        this.idYear = idYear;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
