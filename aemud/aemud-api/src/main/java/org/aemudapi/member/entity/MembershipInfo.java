package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class MembershipInfo {
    private String legacyInstitution;
    private String bacSeries;
    private String bacMention;
    private String yearOfBac;
    private String aemudCourses;
    private String otherCourses;
    private String participatedActivity;
    private String politicOrganisation;
    private LocalDateTime adhesionDate = LocalDateTime.now();
}
