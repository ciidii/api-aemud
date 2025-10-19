package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.PayementMethode;

import java.util.List;
import java.util.UUID;

public record PayementRequestDTO(Double amount, PayementMethode pay_methode, List<String> contributions) {
}
