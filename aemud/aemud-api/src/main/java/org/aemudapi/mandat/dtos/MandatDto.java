package org.aemudapi.mandat.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record MandatDto(
    UUID id,
    String nom,
    LocalDate dateDebut,
    LocalDate dateFin,
    boolean estActif,
    List<PhaseDto> phases
) {}
