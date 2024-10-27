package com.amud.io.aemudapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PersonalInfoDTO {
    private String name;
    private String firstname;
    private String nationality;
    private LocalDate birthday;
    private String maritalStatus;
}
