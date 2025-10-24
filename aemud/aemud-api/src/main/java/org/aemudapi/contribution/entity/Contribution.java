package org.aemudapi.contribution.entity;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.mandat.entity.Phase;
import org.aemudapi.member.entity.BaseEntity;
import org.aemudapi.member.entity.Member;
import java.time.YearMonth;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contribution extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;
    private YearMonth month;
    @Column(nullable = false)
    private Double amountDue;
    private Double amountPaid;
    private ContributionStatus status;
}
