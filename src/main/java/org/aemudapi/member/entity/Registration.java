package org.aemudapi.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registration extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnoreProperties
    private Member member;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    private LocalDate dateInscription = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private TypeInscription registrationType;

    private boolean statusPayment = false;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

}
