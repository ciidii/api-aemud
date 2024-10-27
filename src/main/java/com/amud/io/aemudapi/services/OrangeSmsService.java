package com.amud.io.aemudapi.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface OrangeSmsService {
    public String sendSmsToMultipleRecipients(List<String> recipientNumbers, String message) throws IOException;
    public String sendSms(String recipientNumber, String message) throws IOException;
}
