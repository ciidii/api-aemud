package org.aemudapi.member.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademicInfoRequestDTO {
    private String institutionName;
    private String studiesDomain;
    private String studiesLevel;
}
