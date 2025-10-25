package org.aemudapi.notification.services;

import org.aemudapi.notification.dtos.SmsModelDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SmsModelService {
    ResponseEntity<ResponseVO<SmsModelDTO>> addSmsModel(SmsModelDTO dto);

    ResponseEntity<ResponseVO<List<SmsModelDTO>>> getAllSmsModels();

    ResponseEntity<ResponseVO<Void>> deleteSmsModel(String id);

    ResponseEntity<ResponseVO<SmsModelDTO>> findSmsModelById(String id);
}
