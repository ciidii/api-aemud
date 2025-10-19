package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class PersonalInfo {
    private String name;
    private String firstname;
    private String nationality;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private String gender ="MALE";
}
