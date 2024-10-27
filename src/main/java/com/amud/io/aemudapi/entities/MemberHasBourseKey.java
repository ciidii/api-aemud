package com.amud.io.aemudapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class MemberHasBourseKey implements Serializable {
    @Column(name = "year_")
    private Long year_;
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "bourse_id")
    private Long bourseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberHasBourseKey that = (MemberHasBourseKey) o;
        return year_ == that.year_ && Objects.equals(memberId, that.memberId) && Objects.equals(bourseId, that.bourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year_, memberId, bourseId);
    }


}
