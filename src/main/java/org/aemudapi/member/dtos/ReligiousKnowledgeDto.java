package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.CORAN_LEVEL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReligiousKnowledgeDto {
    private Boolean writeReadArabic;
    private Boolean readArabic;
    private CORAN_LEVEL coranLevel;
    private KnowledgeDto aqida;
    private KnowledgeDto fiqh;
}