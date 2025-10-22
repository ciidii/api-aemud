package org.aemudapi.mandat.dtos;

import java.time.LocalDate;

public record PhaseDto(
    String id,
    String nom,
    LocalDate dateDebut,
    LocalDate dateFin
) {}
