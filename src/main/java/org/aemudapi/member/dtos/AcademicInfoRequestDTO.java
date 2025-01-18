package org.aemudapi.member.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademicInfoRequestDTO {
    private String memberID;
    private String idYear;
    private String university;
    private String faculty;
    private String department;
    private String section;
    private String studiesLevel;
}
