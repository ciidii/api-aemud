package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AddressInfo extends BaseEntity {
    private String addressInDakar;
    private String holidayAddress;
    private String addressToCampus;
}
