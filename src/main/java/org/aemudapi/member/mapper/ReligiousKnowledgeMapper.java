package org.aemudapi.member.mapper;
import org.aemudapi.member.dtos.ReligiousKnowledgeDto;
import org.aemudapi.member.entity.ReligiousKnowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReligiousKnowledgeMapper {

    private final KnowledgeMapper knowledgeMapper;

    @Autowired
    public ReligiousKnowledgeMapper(KnowledgeMapper knowledgeMapper) {
        this.knowledgeMapper = knowledgeMapper;
    }

    public ReligiousKnowledgeDto toDto(ReligiousKnowledge religiousKnowledge) {
        if (religiousKnowledge == null) {
            return null;
        }

        ReligiousKnowledgeDto dto = new ReligiousKnowledgeDto();
        dto.setWriteReadArabic(religiousKnowledge.getWriteReadArabic());
        dto.setReadArabic(religiousKnowledge.getReadArabic());
        dto.setCoranLevel(religiousKnowledge.getCoranLevel());
        dto.setAqida(knowledgeMapper.toDto(religiousKnowledge.getAqida()));
        dto.setFiqh(knowledgeMapper.toDto(religiousKnowledge.getFiqh()));

        return dto;
    }

    public ReligiousKnowledge toEntity(ReligiousKnowledgeDto religiousKnowledgeDto) {
        if (religiousKnowledgeDto == null) {
            return null;
        }

        ReligiousKnowledge religiousKnowledge = new ReligiousKnowledge();
        religiousKnowledge.setWriteReadArabic(religiousKnowledgeDto.getWriteReadArabic());
        religiousKnowledge.setReadArabic(religiousKnowledgeDto.getReadArabic());
        religiousKnowledge.setCoranLevel(religiousKnowledgeDto.getCoranLevel());
        religiousKnowledge.setAqida(knowledgeMapper.toEntity(religiousKnowledgeDto.getAqida()));
        religiousKnowledge.setFiqh(knowledgeMapper.toEntity(religiousKnowledgeDto.getFiqh()));

        return religiousKnowledge;
    }

    public void updateEntityFromDto(ReligiousKnowledgeDto religiousKnowledgeDto, ReligiousKnowledge religiousKnowledge) {
        if (religiousKnowledgeDto == null || religiousKnowledge == null) {
            return;
        }

        religiousKnowledge.setWriteReadArabic(religiousKnowledgeDto.getWriteReadArabic());
        religiousKnowledge.setReadArabic(religiousKnowledgeDto.getReadArabic());
        religiousKnowledge.setCoranLevel(religiousKnowledgeDto.getCoranLevel());

        // Mise à jour des objets embarqués
        if (religiousKnowledgeDto.getAqida() != null) {
            if (religiousKnowledge.getAqida() == null) {
                religiousKnowledge.setAqida(new org.aemudapi.member.entity.Knowledge());
            }
            knowledgeMapper.updateEntityFromDto(religiousKnowledgeDto.getAqida(), religiousKnowledge.getAqida());
        }

        if (religiousKnowledgeDto.getFiqh() != null) {
            if (religiousKnowledge.getFiqh() == null) {
                religiousKnowledge.setFiqh(new org.aemudapi.member.entity.Knowledge());
            }
            knowledgeMapper.updateEntityFromDto(religiousKnowledgeDto.getFiqh(), religiousKnowledge.getFiqh());
        }
    }
}