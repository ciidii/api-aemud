package com.amud.io.aemudapi.dto;


import lombok.Data;

@Data
public class PersonToCallDto {
    private String lastname;
    private String firstname;
    private String requiredNumberPhone;
    private String optionalNumberPhone;
    private String relationship;
}
