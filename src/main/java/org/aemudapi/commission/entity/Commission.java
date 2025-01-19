package org.aemudapi.commission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.aemudapi.member.entity.BaseEntity;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commission extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

