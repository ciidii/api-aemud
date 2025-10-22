package org.aemudapi.mandat.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreatePhaseDto(
    @NotBlank(message = "Le nom de la phase ne peut pas être vide.")
    String nom,

    @NotNull(message = "La date de début est requise.")
    @FutureOrPresent(message = "La date de début ne peut pas être dans le passé.")
    LocalDate dateDebut,

    @NotNull(message = "La date de fin est requise.")
    LocalDate dateFin,

    @NotBlank(message = "L'ID du mandat est requis.")
    String mandatId
) {}
