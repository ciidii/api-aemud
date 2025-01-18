package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class AcademicInfo{
    private String institutionName;
    private String studiesDomain;
    private String studiesLevel;
}
