package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class BourseDTO {
    private Long bourseId;
    private String lebelle;
    private Double montant;
}
