package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.PayementMethode;

import java.util.List;

public record PayementRequestDTO(Double amount, PayementMethode pay_methode, List<String> contributions) {
}
