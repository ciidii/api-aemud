package com.amud.io.aemudapi.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ClubTest {
    private Club club;
    @Test
    void testConstructorWithAllArgs() {
        //given
        Long id = 1L;
        String name = "name";
        //when
        Club club = new Club(id, name);
        //then
        assertThat(id).isEqualTo(club.getId());
        assertThat(name).isEqualTo(club.getName());
    }
    @Test
    void testGetters(){
        Club club = new Club(1L, "Boubacar");
        //when
        Long id = club.getId();
        String name = club.getName();
        //then
        Assertions.assertThat(id).isEqualTo(1L);
        Assertions.assertThat(name).isEqualTo("Boubacar");
    }
    @Test
    void testSetters(){
        Club club = new Club();
        //when
        club.setId(2L);
        club.setName("Diallo");
        //then
        Assertions.assertThat(club.getId()).isEqualTo(2L);
        Assertions.assertThat(club.getName()).isEqualTo("Diallo");
    }
}