package org.aemudapi.member.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressInfo {
    private String addressInDakar;
    private String holidayAddress;
    private String addressToCampus;
}
