package org.aemudapi.notification.dtos;

import lombok.Data;

import java.util.List;


@Data
public class MessageDto {
    private String message;
    private List<String> recipientNumbers;

    public MessageDto() {
        //constructor without args
    }
}