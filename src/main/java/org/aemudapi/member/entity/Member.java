package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.club.entity.Club;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.user.entity.User;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private MembershipInfo membershipInfo;

    @Embedded
    private AcademicInfo academicInfo;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private AddressInfo addressInfo;

    @Embedded
    @Enumerated(EnumType.STRING)
    private ReligiousKnowledge religiousKnowledge;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "membre_commission",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "commission_id")
    )
    private List<Commission> commissions;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "membre_club",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private List<Club> clubs;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "bourse_id")
    private Bourse bourse;

    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "member")
    private List<Registration> registration;

    @OneToOne
    private User user;
}
