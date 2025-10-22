package org.aemudapi.mandat.dtos;

import java.time.LocalDate;
import java.util.List;

public record MandatDto(
        String id,
        String nom,
        LocalDate dateDebut,
        LocalDate dateFin,
        boolean estActif,
        List<PhaseDto> phases
) {
}
