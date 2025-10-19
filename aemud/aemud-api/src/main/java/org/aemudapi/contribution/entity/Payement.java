package org.aemudapi.contribution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payement extends BaseEntity {

    @OneToMany
    private List<Contribution> contributions;
    private Double amount;
    private LocalDate paymentDate = LocalDate.now();
    private PayementMethode pay_methode;
}