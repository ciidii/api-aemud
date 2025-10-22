package org.aemudapi.mandat.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record PhaseDto(
    String id,
    String nom,
    LocalDate dateDebut,
    LocalDate dateFin
) {}
