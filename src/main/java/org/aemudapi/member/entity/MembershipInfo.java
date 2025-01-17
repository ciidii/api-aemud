package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.aemudapi.club.entity.Club;
import org.aemudapi.commission.entity.Commission;

import java.util.List;

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
}
