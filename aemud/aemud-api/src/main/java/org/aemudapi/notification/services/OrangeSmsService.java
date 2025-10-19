package org.aemudapi.notification.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface OrangeSmsService {
    String sendSmsToMultipleRecipients(List<String> recipientNumbers, String message) throws IOException;

    String sendSms(String recipientNumber, String message) throws IOException;
}
