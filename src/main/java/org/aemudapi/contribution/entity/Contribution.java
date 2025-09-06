package org.aemudapi.contribution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Session;

import java.time.LocalDate;
import java.time.Month;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Contribution extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
    private Month month;
    private Double amountDue;
    private Double amountPaid;
    private ContributionStatus status;
}
