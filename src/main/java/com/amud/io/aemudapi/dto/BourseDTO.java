package com.amud.io.aemudapi.dto;

public class BourseDTO {
    private Long bourseId;
    private String lebelle;
    private Double montant;

    public Long getBourseId() {
        return bourseId;
    }

    public void setBourseId(Long bourseId) {
        this.bourseId = bourseId;
    }

    public String getLebelle() {
        return lebelle;
    }

    public void setLebelle(String lebelle) {
        this.lebelle = lebelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
