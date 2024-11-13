package com.amud.io.aemudapi.dto;

import lombok.Data;

@Data
public class ContributionKeyDto {
    private Long idMonth;

    private Long idYear;

    private Long memberId;

    public ContributionKeyDto() {
    }

}
