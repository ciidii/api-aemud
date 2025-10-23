package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationResponseDto {
    private String id;
    private String member;
    private String mandat;
    private LocalDate dateInscription;
    private TypeInscription registrationType;
    private boolean statusPayment;
    private RegistrationStatus registrationStatus;

}
