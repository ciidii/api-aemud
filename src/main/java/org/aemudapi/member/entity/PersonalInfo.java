package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
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
    private String maritalStatus;
}
