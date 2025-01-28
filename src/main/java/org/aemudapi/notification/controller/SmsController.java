package org.aemudapi.notification.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.notification.dtos.MessageDto;
import org.aemudapi.notification.services.OrangeSmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "notifications")
@AllArgsConstructor
public class SmsController {
    private final OrangeSmsService orangeSmsService;

    @PostMapping(path = "sms")
    ResponseEntity<String> sendMessage(@RequestBody MessageDto message) throws IOException {
        this.orangeSmsService.sendSmsToMultipleRecipients(message.getRecipientNumbers(), message.getMessage());
        return new ResponseEntity<>("Message envoyer avec succes", HttpStatus.OK);
    }
}
