package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bourse extends BaseEntity {
    private String lebelle;
    private Double montant;
    @OneToMany(mappedBy = "bourse")
    private List<Member> member;
}
