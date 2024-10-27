package com.amud.io.aemudapi.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class PersonToCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personToCallId;
    private String lastname;
    private String firstname;
    @NotNull
    @NotEmpty
    private String requiredNumberPhone;
    private String optionalNumberPhone;
    private String relationship;
}
