package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.ArabicProficiency;
import org.aemudapi.member.entity.CORAN_LEVEL;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReligiousKnowledgeDto {
    private ArabicProficiency arabicProficiency;
    private CORAN_LEVEL coranLevel;
    private List<KnowledgeDto> aqida;
    private List<KnowledgeDto> fiqhs;
}