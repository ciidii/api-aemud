package com.amud.io.aemudapi.dto;

import com.amud.io.aemudapi.entities.Bourse;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.entities.MemberHasBourseKey;
import com.amud.io.aemudapi.entities.YearOfSession;
import jakarta.persistence.EmbeddedId;

public class MemberHasBourseDto {
    private MemberHasBourseKey memberHasBourseKey;

    public MemberHasBourseKey getMemberHasBourseKey() {
        return memberHasBourseKey;
    }

    public void setMemberHasBourseKey(MemberHasBourseKey memberHasBourseKey) {
        this.memberHasBourseKey = memberHasBourseKey;
    }
}
