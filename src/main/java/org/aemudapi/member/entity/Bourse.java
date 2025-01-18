package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class Bourse extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private String lebelle;
    private Double montant;
}
