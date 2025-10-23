package org.aemudapi.mandat.dtos;

import org.aemudapi.mandat.entity.PhaseStatus;

import java.time.LocalDate;

public record PhaseDto(
    String id,
    String nom,
    LocalDate dateDebut,
    LocalDate dateFin,
    PhaseStatus status
) {}
