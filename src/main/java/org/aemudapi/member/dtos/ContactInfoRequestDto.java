package org.aemudapi.member.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ContactInfoRequestDto {
    private String memberID;
    private String idYear;
    private String numberPhone;
    private String email;
    private List<PersonToCallDto> personToCalls;

}
