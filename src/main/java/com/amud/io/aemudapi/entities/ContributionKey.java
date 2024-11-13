package com.amud.io.aemudapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ContributionKey implements Serializable {
    @Column(name = "id_month")
    private Long idMonth;

    @Column(name = "id_year")
    private Long idYear;

    @Column(name = "id_member")
    private Long memberId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContributionKey that = (ContributionKey) o;
        return Objects.equals(idMonth, that.idMonth) && Objects.equals(idYear, that.idYear) && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMonth, idYear, memberId);
    }
}
