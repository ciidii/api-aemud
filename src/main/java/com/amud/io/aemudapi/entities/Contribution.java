package com.amud.io.aemudapi.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "contribution")
@Data
public class Contribution {

    @EmbeddedId
    private ContributionKey contributionKey;

    @ManyToOne
    @JoinColumn(name = "id_month", insertable = false, updatable = false)
    private MonthContribution contribution;

    @ManyToOne
    @JoinColumn(name = "id_year", insertable = false, updatable = false)
    private YearOfSession yearContribution;


    @JoinColumn(name = "id_member", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;

    @Column(name = "dateHeureContribution", nullable = false)
    private LocalDateTime dateTime;

    private Double amount;

    public Contribution() {
        //constructor without args
    }
}
