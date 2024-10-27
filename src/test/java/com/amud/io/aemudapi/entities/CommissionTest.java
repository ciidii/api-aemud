package com.amud.io.aemudapi.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommissionTest {
    @Test
    void testConstructorWithAllArgs(){
        //given
        Long id= 1L;
        String name = "CFP";
        //when
        Commission commission = new Commission(id, name);
        //then
        assertThat(commission.getId()).isEqualTo(id);
        assertThat(commission.getName()).isEqualTo(name);
    }
    @Test
    void testGetters(){
        //given
        Commission commission = new Commission(1L,"CFP");
        //when
        Long id = commission.getId();
        String name = commission.getName();
        //then
       Assertions.assertThat(id).isEqualTo(1L);
       Assertions.assertThat(name).isEqualTo("CFP");
    }
    @Test
    void testSetters(){
        //given
        Commission commission = new Commission();
        //when
        commission.setId(1L);
        commission.setName("CFP");
        //then
        Assertions.assertThat(commission.getId()).isEqualTo(1L);
        Assertions.assertThat(commission.getName()).isEqualTo("CFP");

    }

}