package org.aemudapi.notification.controller;


import lombok.RequiredArgsConstructor;
import org.aemudapi.notification.dtos.RecipientTemplateDto;
import org.aemudapi.notification.services.SenderTemplateSmsService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sender-template-sms")
@RequiredArgsConstructor
public class SenderTemplateSmsController {

    private final SenderTemplateSmsService senderTemplateSmsService;

    @PostMapping
    public ResponseEntity<ResponseVO<RecipientTemplateDto>> createSenderTemplateSms(@RequestBody RecipientTemplateDto dto) {
        return senderTemplateSmsService.createSenderTemplateSms(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<RecipientTemplateDto>> getSenderTemplateSmsById(@PathVariable String id) {
        return senderTemplateSmsService.getSenderTemplateSmsById(id);
    }


    @GetMapping
    public ResponseEntity<ResponseVO<List<RecipientTemplateDto>>> getAllSenderTemplateSms() {

        return senderTemplateSmsService.getAllSenderTemplateSms();
    }

    @DeleteMapping()
    public ResponseEntity<ResponseVO<Void>> deleteSenderTemplateSms(@RequestParam("id") String id) {

        return senderTemplateSmsService.deleteSenderTemplateSms(id);
    }
}