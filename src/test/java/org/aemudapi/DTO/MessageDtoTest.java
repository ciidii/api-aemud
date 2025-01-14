package org.aemudapi.DTO;

import org.aemudapi.notification.dtos.MessageDto;
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