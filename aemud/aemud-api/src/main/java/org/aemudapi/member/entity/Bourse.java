package org.aemudapi.member.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bourse extends BaseEntity {
    private String lebelle;
    private Double montant;
}

