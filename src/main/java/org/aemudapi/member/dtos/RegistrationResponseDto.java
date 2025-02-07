package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationResponseDto {

    private String member;
    private int session;
    private LocalDate dateInscription;
    private TypeInscription registrationType;
    private boolean statusPayment;
    private RegistrationStatus registrationStatus;

}
