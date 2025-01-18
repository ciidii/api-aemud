package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @ManyToOne
    @JoinColumn(name = "commission_id")
    private Commission commission;

    @ManyToOne()
    @JoinColumn(name = "club_id")
    private Club clubs;
    @ManyToOne
    @JoinColumn(name = "bourse_id")
    private Bourse bourse;
}
