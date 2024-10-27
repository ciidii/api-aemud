package com.amud.io.aemudapi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class ContributionTest {
    private Commission commission;
    private List<Club> clubs;
    private Member member;

    @BeforeEach
    void setUp() {
        commission = new Commission(1L, "Commission");
        clubs = new ArrayList<>();
        clubs.add(new Club(1L, "Club Informatique"));
        clubs.add(new Club(1L, "Club Maths"));
        member = Member.builder()
                .setId(1L)
                .setName("Diallo")
                .setEmail("boubacar@gmail.com")
                .setFirstname("Boubacar")
                .setNationality("SN")
                .setBirthday("10/03/2000")
                .setMaritalStatus("Single")
                .setAddressInDakar("Malika")
                .setHolidayAddress("Dakar")
                .setNumberPhone("7777777777")
                .setPersonToCall("Tanou")
                .setFaculty("FSR")
                .setDepartment("Math-Info")
                .setClubs(clubs)
                .setCommission(commission)
                .setParticipatedActivity("Set Setal")
                .setAddressToCampus("Pavillons H")
                .setAemudCourses("Arabe")
                .setOtherCourses("No")
                .setTwinsName("Loum")
                .setPay(false)
                .build();
    }

    @Test
    void testConstructorWithAllAgrs() {}

    @Test
    void testGetters() {
        //given
    }

    @Test
    void testSetters(){}

}