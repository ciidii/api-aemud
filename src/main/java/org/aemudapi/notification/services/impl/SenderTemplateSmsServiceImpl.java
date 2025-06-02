package org.aemudapi.notification.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aemudapi.notification.dtos.RecipientTemplateDto;
import org.aemudapi.notification.dtos.SenderTemplateSmsMapper;
import org.aemudapi.notification.entity.RecipientTemplate;
import org.aemudapi.notification.repository.SenderTemplateSmsRepository;
import org.aemudapi.notification.services.SenderTemplateSmsService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SenderTemplateSmsServiceImpl implements SenderTemplateSmsService {

    private final SenderTemplateSmsRepository senderTemplateSmsRepository;
    private final SenderTemplateSmsMapper senderTemplateSmsMapper;


    // Opération CRUD: Créer
    @Override
    public ResponseEntity<ResponseVO<RecipientTemplateDto>> createSenderTemplateSms(RecipientTemplateDto dto) {
        RecipientTemplate entity = senderTemplateSmsMapper.toEntity(dto);
        RecipientTemplate savedEntity = senderTemplateSmsRepository.save(entity);
        return ResponseEntity.ok(new ResponseVOBuilder<RecipientTemplateDto>().addData(senderTemplateSmsMapper.toDto(savedEntity)).build());
    }

    // Opération CRUD: Lire (par ID)
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ResponseVO<RecipientTemplateDto>> getSenderTemplateSmsById(String id) {
        return ResponseEntity.ok(senderTemplateSmsRepository.findById(id)
                .map(entity -> new ResponseVOBuilder<RecipientTemplateDto>()
                        .addData(senderTemplateSmsMapper.toDto(entity)).build())
                .orElseThrow(() -> new EntityNotFoundException("Enitiy n'existe pas" + id)));
    }


    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ResponseVO<List<RecipientTemplateDto>>> getAllSenderTemplateSms() {
        List<RecipientTemplateDto> senderTemplateSmsDtos = senderTemplateSmsRepository.findAll().stream().map(senderTemplateSmsMapper::toDto).toList();
        return ResponseEntity.ok(new ResponseVOBuilder<List<RecipientTemplateDto>>().addData(senderTemplateSmsDtos).build());
    }

    // Opération CRUD: Supprimer
    @Override
    public ResponseEntity<ResponseVO<Void>> deleteSenderTemplateSms(String id) {
        senderTemplateSmsRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseVOBuilder<Void>().success().build());
    }
}