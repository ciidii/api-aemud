package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class Bourse extends BaseEntity {
    private String lebelle;
    private Double montant;
}

