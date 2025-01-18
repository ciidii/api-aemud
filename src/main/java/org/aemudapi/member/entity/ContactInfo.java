package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class ContactInfo extends BaseEntity {
    private String numberPhone;
    private String email;
    @OneToMany
    @JoinTable(name = "persontocall_contact")
    private List<PersonToCall> personToCalls;
}
