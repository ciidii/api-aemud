package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class AddressInfoRequestDto {
    private String memberID;
    private String idYear;
    private String addressInDakar;
    private String holidayAddress;
    private String addressToCampus;
}
