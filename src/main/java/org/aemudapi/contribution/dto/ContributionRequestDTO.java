package org.aemudapi.contribution.dto;

import org.aemudapi.contribution.entity.ContributionStatus;

import java.time.Month;
import java.util.UUID;

public record ContributionRequestDTO(
        UUID memberId, UUID session, Month month, double amountDue, double amountPaid,
        ContributionStatus status) {
}
