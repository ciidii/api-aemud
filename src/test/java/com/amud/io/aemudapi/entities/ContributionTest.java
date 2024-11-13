package com.amud.io.aemudapi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class ContributionTest {

    private List<Club> clubs;

    @BeforeEach
    void setUp() {
        clubs = new ArrayList<>();
        clubs.add(new Club(1L, "Club Informatique"));
        clubs.add(new Club(1L, "Club Maths"));
    }

    @Test
    void testConstructorWithAllAgrs() {
        //given
    }

    @Test
    void testGetters() {
        //given
    }

    @Test
    void testSetters() {
        //given
    }

}