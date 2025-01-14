package org.aemudapi.member.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "club")
@Data
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Club() {
        //constructor without args
    }

    public Club(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}