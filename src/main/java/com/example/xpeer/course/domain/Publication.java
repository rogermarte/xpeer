package com.example.xpeer.course.domain;

import java.time.LocalDate;

public class Publication {
    private LocalDate date;

    public Publication(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
