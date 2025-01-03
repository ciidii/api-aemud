package com.amud.io.aemudapi.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ContactInfo {
    @Id
    @EmbeddedId
    private MemberAndYearKey memberAndYearKey;
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "year_", insertable = false, updatable = false)
    private YearOfSession year;
    private String numberPhone;
    private String email;
    @OneToMany
    @JoinTable(name = "persontocall_contact")
    private List<PersonToCall> personToCalls;
}
