package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoRequestDto {
    private String numberPhone;
    private String email;
    private PersonToCallDto personToCall;

}
