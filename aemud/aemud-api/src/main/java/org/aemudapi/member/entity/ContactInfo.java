package org.aemudapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Embeddable
public class ContactInfo {
    @Column(unique = true, nullable = false)
    private String numberPhone;
    @Column(unique = true, nullable = false)
    private String email;
    @ElementCollection
    private List<PersonToCall> personToCalls;
}
