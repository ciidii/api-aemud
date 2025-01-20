package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;

@Getter
@Setter
public class RegistrationRequestDto {
    private String id;
    private String member;
    private String session;
    private TypeInscription registrationType;
    private boolean statusPayment;
    private RegistrationStatus registrationStatus;
}
