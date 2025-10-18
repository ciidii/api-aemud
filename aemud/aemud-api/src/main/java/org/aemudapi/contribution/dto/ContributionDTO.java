package org.aemudapi.contribution.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ContributionDTO {
    private String contributionId;
    private String memberId;

    private String sessionId;
    private String monthId;

    private Double amount;
    private LocalDate paymentDate;
}
