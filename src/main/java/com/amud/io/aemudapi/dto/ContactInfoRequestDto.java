package com.amud.io.aemudapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContactInfoRequestDto {
    private Long memberID;
    private Long idYear;
    private String numberPhone;
    private String email;
    private List<PersonToCallDto> personToCalls;

}
