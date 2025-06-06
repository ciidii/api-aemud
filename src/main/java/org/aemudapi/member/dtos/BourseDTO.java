package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Getter
@Setter
public class BourseDTO {
    private List<String> members;
    private String bourseId;
    private String lebelle;
    private Double montant;
}
