package com.amud.io.aemudapi.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleTest {
    @BeforeEach
    void setUp() {
    }
    @Test
    void testConstructorWithAllArgs(){
        //given
        Long id = 1L;
        String name = "ROLE_ADMIN";
        //when
        Role role = new Role(id, name);
        //then
        assertThat(role.getId()).isEqualTo(id);
    }

    @Test
    void testGetters(){
        //given
        Role role = new Role(1L, "ROLE_ADMIN");
        //when
        Long id = role.getId();
        String name = role.getName();
        //then
        Assertions.assertThat(id).isEqualTo(1L);
        Assertions.assertThat(name).isEqualTo("ROLE_ADMIN");
    }
    @Test
    void testSetters(){
        //given
        Role role=new Role();
        //when
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        //then
        Assertions.assertThat(role.getId()).isEqualTo(1L);
        Assertions.assertThat(role.getName()).isEqualTo("ROLE_ADMIN");
    }
}