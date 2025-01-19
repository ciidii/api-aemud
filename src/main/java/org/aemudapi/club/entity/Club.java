package org.aemudapi.club.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Club extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "clubs", cascade = {CascadeType.PERSIST})
    private List<Member> members;
}

