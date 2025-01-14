package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AddressInfo {
    @Id
    @EmbeddedId
    private MemberAndYearKey memberAndYearKey;
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;
    private String addressInDakar;
    private String holidayAddress;
    private String addressToCampus;
}
