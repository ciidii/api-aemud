package org.aemudapi.member.mapper;
import org.aemudapi.member.dtos.KnowledgeDto;
import org.aemudapi.member.entity.Knowledge;
import org.springframework.stereotype.Component;

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

    public void updateEntityFromDto(KnowledgeDto knowledgeDto, Knowledge knowledge) {
        if (knowledgeDto == null || knowledge == null) {
            return;
        }

        knowledge.setAcquired(knowledgeDto.getAcquired());
        knowledge.setBookName(knowledgeDto.getBookName());
        knowledge.setTeacherName(knowledgeDto.getTeacherName());
        knowledge.setLearningPlace(knowledgeDto.getLearningPlace());
    }
}