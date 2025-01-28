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
    @ManyToOne
    @JoinColumn(name = "month_id", nullable = false)
    private Month month;

    private Double amount;
    private LocalDate paymentDate = LocalDate.now();
}
