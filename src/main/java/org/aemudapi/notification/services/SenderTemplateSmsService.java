package org.aemudapi.notification.services;

import org.aemudapi.notification.dtos.RecipientTemplateDto;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SenderTemplateSmsService {

    ResponseEntity<ResponseVO<RecipientTemplateDto>> createSenderTemplateSms(RecipientTemplateDto dto);


    @Transactional(readOnly = true)
    ResponseEntity<ResponseVO<RecipientTemplateDto>> getSenderTemplateSmsById(String id);


    @Transactional(readOnly = true)
    ResponseEntity<ResponseVO<List<RecipientTemplateDto>>> getAllSenderTemplateSms();


    ResponseEntity<ResponseVO<Void>> deleteSenderTemplateSms(String id);
}
