package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class AcademicInfoRequestDTO {
    private Long memberID;
    private Long idYear;
    private String university;
    private String faculty;
    private String department;
    private String section;
    private String studiesLevel;
}
