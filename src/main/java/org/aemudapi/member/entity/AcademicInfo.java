package org.aemudapi.member.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicInfo extends BaseEntity {
    private String university;
    private String faculty;
    private String department;
    private String section;
    private String studiesLevel;
}
