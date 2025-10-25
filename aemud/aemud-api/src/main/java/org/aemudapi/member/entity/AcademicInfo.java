package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class AcademicInfo {
    private String institutionName;
    private String studiesDomain;
    private String studiesLevel;
}
