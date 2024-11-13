package com.amud.io.aemudapi.DTO;

import com.amud.io.aemudapi.dto.MessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageDtoTest {
    MessageDto messageDto;

    @BeforeEach
    public void setUp() {
        messageDto = new MessageDto();
    }

    @Test
    void testGettersAndSetters() {
        //when
        messageDto.setMessage("Hello World");
        //then
        assertEquals("Hello World", messageDto.getMessage());
    }
}