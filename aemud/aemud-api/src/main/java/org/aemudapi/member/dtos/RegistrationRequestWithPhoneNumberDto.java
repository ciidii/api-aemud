package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;

@Getter
@Setter
public class RegistrationRequestWithPhoneNumberDto {
    private String id;
    private String memberPhoneNumber;
    private String mandatId;
    private TypeInscription registrationType;
    private boolean statusPayment;
    private RegistrationStatus registrationStatus;
}
