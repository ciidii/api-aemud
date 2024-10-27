package com.amud.io.aemudapi.dto;

import java.util.Collections;
import java.util.List;


public class MessageDto {
    private String message;
    private List<String> recipientNumbers;

    public MessageDto() {
        //constructor without args
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getRecipientNumbers() {
        return recipientNumbers;
    }

    public void setRecipientNumbers(String recipientNumbers) {
        this.recipientNumbers = Collections.singletonList(recipientNumbers);
    }
}
