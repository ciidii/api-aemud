package org.aemudapi.commission.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.aemudapi.member.entity.Member;

import java.util.List;

@Entity
@Data
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "commission")
    private List<Member> member;
    public Commission() {
        //constructor without args
    }

    public Commission(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
