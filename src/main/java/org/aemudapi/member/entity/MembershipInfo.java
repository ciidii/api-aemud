package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Embeddable
@Data
public class MembershipInfo {
    @ManyToOne
    private YearOfSession yearOfMembership;
    private boolean pay;
    private String legacyInstitution;
    private String bacSeries;
    private String bacMention;
    private String yearOfBac;
    private String aemudCourses;
    private String otherCourses;
    private String participatedActivity;
    private String politicOrganisation;
    @ManyToOne
    private Commission commission;
    @ManyToMany
    private List<Club> clubs;

    @ManyToOne
    private Bourse bourse;
}
