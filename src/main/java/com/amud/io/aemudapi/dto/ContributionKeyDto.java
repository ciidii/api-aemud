package com.amud.io.aemudapi.dto;

public class ContributionKeyDto {
    private Long idMonth;

    private Long idYear;

    private Long memberId;

    public ContributionKeyDto() {
    }

    public Long getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(Long idMonth) {
        this.idMonth = idMonth;
    }

    public Long getIdYear() {
        return idYear;
    }

    public void setIdYear(Long idYear) {
        this.idYear = idYear;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
