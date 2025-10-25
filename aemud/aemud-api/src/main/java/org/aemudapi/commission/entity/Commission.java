package org.aemudapi.commission.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commission extends BaseEntity {
    private String name;

}

