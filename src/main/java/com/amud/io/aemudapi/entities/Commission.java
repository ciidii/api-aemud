package com.amud.io.aemudapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Commission() {
        //constructor without args
    }

    public Commission(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
