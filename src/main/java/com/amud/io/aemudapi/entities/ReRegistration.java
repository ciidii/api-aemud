package com.amud.io.aemudapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReRegistration {
    @Id
    @EmbeddedId
    private MemberAndYearKey memberAndYearKey;
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "year_of_registration", insertable = false, updatable = false)
    private YearOfSession year;
}
