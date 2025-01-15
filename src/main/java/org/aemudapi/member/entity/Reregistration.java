package org.aemudapi.member.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Reregistration {
    @Id
    private Long id;

    @OneToOne
    private AcademicInfo academicInfo;

    @OneToOne
    private AddressInfo address;

    @OneToOne
    private ContactInfo contactInfo;
}
