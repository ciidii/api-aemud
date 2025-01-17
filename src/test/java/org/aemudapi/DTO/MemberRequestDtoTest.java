package org.aemudapi.DTO;

import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.club.entity.Club;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemberRequestDtoTest {
    MemberRequestDto memberRequestDto;
    private List<Club> clubs;

    @BeforeEach
    void setUp() {
        memberRequestDto = new MemberRequestDto();
        clubs = new ArrayList<>();
        clubs.add(new Club(1L, "Club Informatique"));
        clubs.add(new Club(1L, "Club Maths"));
    }

    @Test
    void testSetterAndSetters() {
        memberRequestDto.setId(1L);
        //then
        assertThat(memberRequestDto.getId()).isEqualTo(1L);
    }
}