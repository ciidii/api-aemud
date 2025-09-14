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
        dto.setArabicProficiency(religiousKnowledge.getArabicProficiency());
        dto.setCoranLevel(religiousKnowledge.getCoranLevel());
        dto.setAqida(knowledgeMapper.toDto(religiousKnowledge.getAqidaBooks()));
        dto.setFiqhs(knowledgeMapper.toDto(religiousKnowledge.getFiqhBooks()));
        return dto;
    }

    public ReligiousKnowledge toEntity(ReligiousKnowledgeDto religiousKnowledgeDto) {
        if (religiousKnowledgeDto == null) {
            return null;
        }

        ReligiousKnowledge religiousKnowledge = new ReligiousKnowledge();
        religiousKnowledge.setArabicProficiency(religiousKnowledgeDto.getArabicProficiency());
        religiousKnowledge.setAqidaBooks(this.knowledgeMapper.toEntity(religiousKnowledgeDto.getAqida()));
        religiousKnowledge.setCoranLevel(religiousKnowledgeDto.getCoranLevel());
        religiousKnowledge.setFiqhBooks(knowledgeMapper.toEntity(religiousKnowledgeDto.getFiqhs()));

        return religiousKnowledge;
    }


}