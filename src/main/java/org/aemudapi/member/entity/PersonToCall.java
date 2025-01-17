package org.aemudapi.member.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PersonToCall extends BaseEntity {
    private String lastname;
    private String firstname;
    @NotNull
    @NotEmpty
    private String requiredNumberPhone;
    private String optionalNumberPhone;
    private String relationship;
}
