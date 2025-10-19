package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.KnowledgeDto;
import org.aemudapi.member.entity.Knowledge;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KnowledgeMapper {

    public KnowledgeDto toDto(Knowledge knowledge) {
        if (knowledge == null) {
            return null;
        }

        KnowledgeDto dto = new KnowledgeDto();
        dto.setAcquired(knowledge.getAcquired());
        dto.setBookName(knowledge.getBookName());
        dto.setTeacherName(knowledge.getTeacherName());
        dto.setLearningPlace(knowledge.getLearningPlace());

        return dto;
    }

    public Knowledge toEntity(KnowledgeDto knowledgeDto) {
        if (knowledgeDto == null) {
            return null;
        }

        Knowledge knowledge = new Knowledge();
        knowledge.setAcquired(knowledgeDto.getAcquired());
        knowledge.setBookName(knowledgeDto.getBookName());
        knowledge.setTeacherName(knowledgeDto.getTeacherName());
        knowledge.setLearningPlace(knowledgeDto.getLearningPlace());

        return knowledge;
    }

    public List<Knowledge> toEntity(List<KnowledgeDto> knowledgeDtoList) {
        if (knowledgeDtoList == null) {
            return Collections.emptyList();
        }
        return knowledgeDtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<KnowledgeDto> toDto(List<Knowledge> knowledgeList) {
        if (knowledgeList == null || knowledgeList.isEmpty()) {
            return Collections.emptyList();
        }
        return knowledgeList.stream().map(this::toDto).collect(Collectors.toList());
    }

}