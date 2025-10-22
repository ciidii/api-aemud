package org.aemudapi.mandat.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateMandatDto(
    @NotBlank(message = "Le nom du mandat ne peut pas être vide.")
    String nom,

    @NotNull(message = "La date de début est requise.")
    @FutureOrPresent(message = "La date de début ne peut pas être dans le passé.")
    LocalDate dateDebut,

    @NotNull(message = "La date de fin est requise.")
    @Future(message = "La date de fin doit être dans le futur.")
    LocalDate dateFin
) {}
