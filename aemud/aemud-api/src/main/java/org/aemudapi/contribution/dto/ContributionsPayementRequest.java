package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.PayementMethode;

import java.util.List;

public record ContributionsPayementRequest(List<String> contributionsID, PayementMethode payementMethode) {
}
