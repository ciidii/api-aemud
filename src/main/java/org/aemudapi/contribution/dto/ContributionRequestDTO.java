package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.ContributionStatus;

import java.time.Month;

public record ContributionRequestDTO(String sessionID,
                                     String memberID,
                                     Month month,
                                     double amountDue,
                                     double amountPaid,
                                     ContributionStatus status) {
}
