package org.aemudapi.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class YearOfSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idYear;
    @NotNull
    private int year_;
    private boolean currentYear;
    public YearOfSession() {
        //
    }
}
