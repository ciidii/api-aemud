package com.amud.io.aemudapi.buiders;


import com.amud.io.aemudapi.entities.Member;


public class MemberBuilder {

    private Member member = new Member();

    public MemberBuilder setId(Long id) {
        member.setId(id);
        return this;
    }

    public Member build() {
        return member;
    }
}
