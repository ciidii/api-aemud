package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.PayementMethode;

import java.time.LocalDate;

public record PayementResponseDTO(Double amount, LocalDate paymentDate, PayementMethode pay_methode) {
}
