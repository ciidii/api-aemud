package org.aemudapi.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Club extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "clubs")
    private List<Member> member;
}