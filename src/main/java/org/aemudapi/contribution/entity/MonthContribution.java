package org.aemudapi.contribution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MonthContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonth;
    private String wording;

    public MonthContribution() {
        //constrictor without args ;;
    }
}
