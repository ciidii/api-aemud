package org.aemudapi.notification.dtos;

import org.aemudapi.notification.entity.RecipientTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SenderTemplateSmsMapper {

    public RecipientTemplateDto toDto(RecipientTemplate entity) {
        if (entity == null) {
            return null;
        }

        RecipientTemplateDto dto = new RecipientTemplateDto();
        dto.setId(entity.getId());
        dto.setRecipient(entity.getSenders());
        dto.setTemplateName(entity.getTemplateName());

        return dto;
    }

    public List<RecipientTemplateDto> toDto(List<RecipientTemplate> entities) {
        if (entities == null) {
            return null;
        }
        List<RecipientTemplateDto> dtos = new ArrayList<RecipientTemplateDto>();
        entities.stream().map(this::toDto).forEach(dtos::add);
        return dtos;
    }

    public RecipientTemplate toEntity(RecipientTemplateDto dto) {
        if (dto == null) {
            return null;
        }

        RecipientTemplate entity = new RecipientTemplate();
        entity.setId(dto.getId()); // Attention : en général, on ne set pas l'ID manuellement si c'est auto-généré
        entity.setSenders(dto.getRecipient());
        entity.setTemplateName(dto.getTemplateName());

        return entity;
    }
}
