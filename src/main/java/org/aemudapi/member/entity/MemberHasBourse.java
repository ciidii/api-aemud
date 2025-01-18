package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberHasBourse {
    @EmbeddedId
    private MemberHasBourseKey memberHasBourseKey;

    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;

    @JoinColumn(name = "bourse_id", insertable = false, updatable = false)
    @ManyToOne
    private Bourse bourse;

    @JoinColumn(name = "year_", insertable = false, updatable = false)
    @OneToOne
    private Session year;
}
