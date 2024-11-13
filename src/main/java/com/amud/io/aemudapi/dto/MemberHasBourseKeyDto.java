package com.amud.io.aemudapi.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MemberHasBourseKeyDto {
    private Long year_;
    private Long memberId;
    private Long bourseId;

    public MemberHasBourseKeyDto() {
        //without args
    }
}
