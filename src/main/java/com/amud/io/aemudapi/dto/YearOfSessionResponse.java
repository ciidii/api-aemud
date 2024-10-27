package com.amud.io.aemudapi.dto;

public class YearOfSessionResponse {
    private Long id;
    private int year_;
    private boolean currentYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
