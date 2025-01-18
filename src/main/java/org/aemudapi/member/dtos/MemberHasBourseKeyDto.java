package org.aemudapi.member.dtos;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MemberHasBourseKeyDto {
    private String year_;
    private String memberId;
    private String bourseId;

    public MemberHasBourseKeyDto() {
        //without args
    }
}
