package com.amud.io.aemudapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContributionResponseDto {
    private String month;

    private Long idYear;

    private String memberId;

    private LocalDateTime dateTime;

    private Double Amount;
}
