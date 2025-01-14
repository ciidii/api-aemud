package org.aemudapi.contribution.controller;

import lombok.Data;

@Data
public class ContributionKeyDto {
    private Long idMonth;

    private Long idYear;

    private Long memberId;

    public ContributionKeyDto() {
    }

}
