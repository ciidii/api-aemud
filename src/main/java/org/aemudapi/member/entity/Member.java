package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.club.entity.Club;
import org.aemudapi.commission.entity.Commission;

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

    @OneToMany(mappedBy = "member")
    private List<Commission> commissions;

    @OneToMany(mappedBy = "member")
    private List<Club> clubs;

    @ManyToOne
    @JoinColumn(name = "bourse_id")
    private Bourse bourse;
}
