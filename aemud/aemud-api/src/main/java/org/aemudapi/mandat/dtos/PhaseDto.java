package org.aemudapi.mandat.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record PhaseDto(
    UUID id,
    String nom,
    LocalDate dateDebut,
    LocalDate dateFin
) {}
