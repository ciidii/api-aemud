package org.aemudapi.member.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class PersonToCall {
    private String personToCallName;
    private String personToCallFirstname;
    @NotNull
    @NotEmpty
    private String personToCallRequiredNumberPhone;
    private String personToCallOptionalNumberPhone;
    private String relationship;
}
