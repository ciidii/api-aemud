package org.aemudapi.member.dtos;

import lombok.Data;
import org.aemudapi.member.entity.MaritalStatus;

import java.time.LocalDate;

@Data
public class PersonalInfoDTO {
    private String name;
    private String firstname;
    private String nationality;
    private String gender;
    private LocalDate birthday;
    private MaritalStatus maritalStatus;
}
