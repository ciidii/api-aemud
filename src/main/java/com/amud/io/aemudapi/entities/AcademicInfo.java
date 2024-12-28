package com.amud.io.aemudapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicInfo {
    @Id
    @EmbeddedId
    private MemberAndYearKey memberAndYearKey;
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "year_", insertable = false, updatable = false)
    private YearOfSession year;
    private String university;
    private String faculty;
    private String department;
    private String section;
    private String studiesLevel;
}
