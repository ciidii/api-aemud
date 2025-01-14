package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class PersonalInfo {
    private String name;
    private String firstname;
    private String nationality;
    private LocalDate birthday;
    private String maritalStatus;
}
