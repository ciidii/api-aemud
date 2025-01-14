package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class AddressInfoRequestDto {
    private Long memberID;
    private Long idYear;
    private String addressInDakar;
    private String holidayAddress;
    private String addressToCampus;
}
