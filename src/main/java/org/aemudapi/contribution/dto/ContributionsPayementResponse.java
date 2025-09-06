package org.aemudapi.contribution.dto;

import java.util.List;

public record ContributionsPayementResponse(PayementResponseDTO payements,
                                            List<ContributionResponseDTO> contributions) {
}
