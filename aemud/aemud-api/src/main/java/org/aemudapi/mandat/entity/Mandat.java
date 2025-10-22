package org.aemudapi.mandat.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Mandat extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String nom; // Ex: "Mandat 2025-2027"

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    private boolean estActif;

    @OneToMany(mappedBy = "mandat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phase> phases;
}