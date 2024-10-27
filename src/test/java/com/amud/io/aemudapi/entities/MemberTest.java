package com.amud.io.aemudapi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    private Commission commission;
    private List<Club> clubs;
    Member member;

    @BeforeEach
    void setUp() {
        commission = new Commission(1L, "Commission");
        clubs = new ArrayList<>();
        clubs.add(new Club(1L, "Club Informatique"));
        clubs.add(new Club(1L, "Club Maths"));
        //
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
                .setPoliticOrganisation("PASTEF")
                .build();
    }

    @Test
    void testSetters() {
        //then
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getName()).isEqualTo("Diallo");
        assertThat(member.getEmail()).isEqualTo("boubacar@gmail.com");
        assertThat(member.getFirstname()).isEqualTo("Boubacar");
        assertThat(member.getNationality()).isEqualTo("SN");
        assertThat(member.getBirthday()).isEqualTo("10/03/2000");
        assertThat(member.getMaritalStatus()).isEqualTo("Single");
        assertThat(member.getAddressInDakar()).isEqualTo("Malika");
        assertThat(member.getHolidayAddress()).isEqualTo("Dakar");
        assertThat(member.getNumberPhone()).isEqualTo("7777777777");
        assertThat(member.getPersonToCall()).isEqualTo("Tanou");
        assertThat(member.getFaculty()).isEqualTo("FSR");
        assertThat(member.getDepartment()).isEqualTo("Math-Info");
        assertThat(member.getClubs()).isEqualTo(clubs);
        assertThat(member.getCommission()).isEqualTo(commission);
        assertThat(member.getParticipatedActivity()).isEqualTo("Set Setal");
        assertThat(member.getAddressToCampus()).isEqualTo("Pavillons H");
        assertThat(member.getAemudCourses()).isEqualTo("Arabe");
        assertThat(member.getOtherCourses()).isEqualTo("No");
        assertThat(member.getYearOfMembership()).isEqualTo("2019");
        assertThat(member.getTwinsName()).isEqualTo("Loum");
        assertThat(member.getPay()).isFalse();
        assertThat(member.getPoliticOrganisation()).isEqualTo("PASTEF");
    }

    @Test
    void testBuilder() {
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getName()).isEqualTo("Diallo");
        assertThat(member.getEmail()).isEqualTo("boubacar@gmail.com");
        assertThat(member.getFirstname()).isEqualTo("Boubacar");
        assertThat(member.getNationality()).isEqualTo("SN");
        assertThat(member.getBirthday()).isEqualTo("10/03/2000");
        assertThat(member.getMaritalStatus()).isEqualTo("Single");
        assertThat(member.getAddressInDakar()).isEqualTo("Malika");
        assertThat(member.getHolidayAddress()).isEqualTo("Dakar");
        assertThat(member.getNumberPhone()).isEqualTo("7777777777");
        assertThat(member.getPersonToCall()).isEqualTo("Tanou");
        assertThat(member.getFaculty()).isEqualTo("FSR");
        assertThat(member.getDepartment()).isEqualTo("Math-Info");
        assertThat(member.getClubs()).isEqualTo(clubs);
        assertThat(member.getCommission()).isEqualTo(commission);
        assertThat(member.getParticipatedActivity()).isEqualTo("Set Setal");
        assertThat(member.getAddressToCampus()).isEqualTo("Pavillons H");
        assertThat(member.getAemudCourses()).isEqualTo("Arabe");
        assertThat(member.getOtherCourses()).isEqualTo("No");
        assertThat(member.getYearOfMembership()).isEqualTo("2019");
        assertThat(member.getTwinsName()).isEqualTo("Loum");
        assertThat(member.getPay()).isFalse();
    }
}