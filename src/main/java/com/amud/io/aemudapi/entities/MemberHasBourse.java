package com.amud.io.aemudapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MemberHasBourse {
    @EmbeddedId
   private MemberHasBourseKey memberHasBourseKey;

    @JoinColumn(name = "member_id",insertable = false,updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;

    @JoinColumn(name = "bourse_id",insertable = false,updatable = false)
    @ManyToOne
    private Bourse bourse;

    @JoinColumn(name = "year_",insertable = false,updatable = false)
    @OneToOne
    private YearOfSession year;
}
