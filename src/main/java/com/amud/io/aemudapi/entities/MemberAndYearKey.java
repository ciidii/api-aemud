package com.amud.io.aemudapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAndYearKey implements Serializable {
    @Column(name = "year_of_registration")
    Long yearOfRegistration;
    @Column(name = "member_id")
    Long member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAndYearKey that = (MemberAndYearKey) o;
        return Objects.equals(yearOfRegistration, that.yearOfRegistration) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearOfRegistration, member);
    }
}
