package org.aemudapi.contribution.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;


@Getter
@Setter
@NoArgsConstructor
public class Month extends BaseEntity {
    private String monthName;
    private int MonthNumber;
}
