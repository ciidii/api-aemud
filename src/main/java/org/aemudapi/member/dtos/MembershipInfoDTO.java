package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class MembershipInfoDTO {
    private String legacyInstitution;
    private String bacSeries;
    private String bacMention;
    private String yearOfBac;
    private String aemudCourses;
    private String otherCourses;
    private String participatedActivity;
    private String politicOrganisation;
}
