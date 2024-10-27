package com.amud.io.aemudapi.DTO;

import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.entities.Club;
import com.amud.io.aemudapi.entities.Commission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemberRequestDtoTest {
    private Commission commission;
    private List<Club> clubs;
    MemberRequestDto memberRequestDto;
    @BeforeEach
    void setUp() {
        memberRequestDto = new MemberRequestDto();
        commission = new Commission(1L, "Commission");
        clubs = new ArrayList<>();
        clubs.add(new Club(1L, "Club Informatique"));
        clubs.add(new Club(1L, "Club Maths"));
    }
    @Test
    void testSetterAndSetters(){
                memberRequestDto.setId(1L);
                memberRequestDto.setName("Diallo");
                memberRequestDto.setEmail("boubacar@gmail.com");
                memberRequestDto.setFirstname("Boubacar");
                memberRequestDto.setNationality("SN");
                memberRequestDto.setBirthday("10/03/2000");
                memberRequestDto.setMaritalStatus("Single");
                memberRequestDto.setAddressInDakar("Malika");
                memberRequestDto.setHolidayAddress("Dakar");
                memberRequestDto.setNumberPhone("7777777777");
                memberRequestDto.setPersonToCall("Tanou");
                memberRequestDto.setFaculty("FSR");
                memberRequestDto.setDepartment("Math-Info");
                memberRequestDto.setClubs(clubs);
                memberRequestDto.setCommission(commission);
                memberRequestDto.setParticipatedActivity("Set Setal");
                memberRequestDto.setAddressToCampus("Pavillons H");
                memberRequestDto.setAemudCourses("Arabe");
                memberRequestDto.setTwinsName("Loum");
                memberRequestDto.setPay(false);
                memberRequestDto.setPoliticOrganisation("PASTEF");
        //then
        assertThat(memberRequestDto.getId()).isEqualTo(1L);
        assertThat(memberRequestDto.getName()).isEqualTo("Diallo");
        assertThat(memberRequestDto.getEmail()).isEqualTo("boubacar@gmail.com");
        assertThat(memberRequestDto.getFirstname()).isEqualTo("Boubacar");
        assertThat(memberRequestDto.getNationality()).isEqualTo("SN");
        assertThat(memberRequestDto.getBirthday()).isEqualTo("10/03/2000");
        assertThat(memberRequestDto.getMaritalStatus()).isEqualTo("Single");
        assertThat(memberRequestDto.getAddressInDakar()).isEqualTo("Malika");
        assertThat(memberRequestDto.getHolidayAddress()).isEqualTo("Dakar");
        assertThat(memberRequestDto.getNumberPhone()).isEqualTo("7777777777");
        assertThat(memberRequestDto.getPersonToCall()).isEqualTo("Tanou");
        assertThat(memberRequestDto.getFaculty()).isEqualTo("FSR");
        assertThat(memberRequestDto.getDepartment()).isEqualTo("Math-Info");
        assertThat(memberRequestDto.getClubs()).isEqualTo(clubs);
        assertThat(memberRequestDto.getCommission()).isEqualTo(commission);
        assertThat(memberRequestDto.getBourse()).isEqualTo("bourse_entier");
        assertThat(memberRequestDto.getParticipatedActivity()).isEqualTo("Set Setal");
        assertThat(memberRequestDto.getAddressToCampus()).isEqualTo("Pavillons H");
        assertThat(memberRequestDto.getAemudCourses()).isEqualTo("Arabe");
        assertThat(memberRequestDto.getOtherCourses()).isEqualTo("No");
        assertThat(memberRequestDto.getYearOfMembership()).isEqualTo("2019");
        assertThat(memberRequestDto.getTwinsName()).isEqualTo("Loum");
        assertThat(memberRequestDto.getPay()).isFalse();
        assertThat(memberRequestDto.getPoliticOrganisation()).isEqualTo("PASTEF");
    }
}