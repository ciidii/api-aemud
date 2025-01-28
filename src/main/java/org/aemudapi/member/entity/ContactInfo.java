package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Embeddable
public class ContactInfo {
    private String numberPhone;
    private String email;
    @ElementCollection
    private List<PersonToCall> personToCalls;
}
