package org.aemudapi.member.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalInfoDTO {
    private String name;
    private String firstname;
    private String nationality;
    private LocalDate birthday;
    private String maritalStatus;
}