package org.aemudapi.mandat.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateMandatDto(
        @NotBlank(message = "Le nom du mandat ne peut pas être vide.")
        String nom,

        @NotNull(message = "La date de début est requise.")
        LocalDate dateDebut,

        @NotNull(message = "La date de fin est requise.")
        LocalDate dateFin
) {
}
