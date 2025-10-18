package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.ContributionStatus;

import java.time.Month;
import java.time.YearMonth;

public record ContributionResponseDTO(String id, String sessionID, String memberID, YearMonth month, double amountDue,
                                      double amountPaid,
                                      ContributionStatus status) {
}
