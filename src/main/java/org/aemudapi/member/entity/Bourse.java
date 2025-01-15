package org.aemudapi.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBourse;
    private String lebelle;
    private Double montant;

    public Bourse() {
        //construtor without Args
    }
}
