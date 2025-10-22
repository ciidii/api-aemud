package org.aemudapi.mandat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Phase extends BaseEntity {

    @Column(nullable = false)
    private String nom; // Ex: "Année 1: 2025-2026"

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mandat_id")
    private Mandat mandat;
}