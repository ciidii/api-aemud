package com.amud.io.aemudapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
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
