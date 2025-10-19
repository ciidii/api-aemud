package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;

@Getter
@Builder
public class FilterDTO {
    private String club;
    private String year;
    private String commission;
    private String bourse;
    private RegistrationStatus registrationStatus;
    private String statusPayment;
    private String registration;
    private TypeInscription registrationType;
}
