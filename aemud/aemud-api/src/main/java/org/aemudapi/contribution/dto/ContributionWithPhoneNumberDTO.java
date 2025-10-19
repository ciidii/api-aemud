package org.aemudapi.contribution.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContributionWithPhoneNumberDTO {
    private String phoneNumber;
    private String monthId;
    private String sessionId;
}
