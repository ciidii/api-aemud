package com.amud.io.aemudapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
public class YearOfSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idYear;
    @Unique
    @NotNull
    private int year_;
    private boolean currentYear;

    public YearOfSession() {
        //
    }

    public Long getIdYear() {
        return idYear;
    }

    public void setIdYear(Long idYear) {
        this.idYear = idYear;
    }

    public int getYear_() {
        return year_;
    }

    public void setYear_(int year_) {
        this.year_ = year_;
    }

    public boolean isCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(boolean currentYear) {
        this.currentYear = currentYear;
    }
}
