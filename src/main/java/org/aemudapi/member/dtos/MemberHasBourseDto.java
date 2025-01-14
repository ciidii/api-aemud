package org.aemudapi.member.dtos;

import org.aemudapi.member.entity.MemberHasBourseKey;

public class MemberHasBourseDto {
    private MemberHasBourseKey memberHasBourseKey;

    public MemberHasBourseKey getMemberHasBourseKey() {
        return memberHasBourseKey;
    }

    public void setMemberHasBourseKey(MemberHasBourseKey memberHasBourseKey) {
        this.memberHasBourseKey = memberHasBourseKey;
    }
}
