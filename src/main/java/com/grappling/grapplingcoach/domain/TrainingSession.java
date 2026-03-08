package com.grappling.grapplingcoach.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.persistence.Column;


@Entity
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private LocalDate date;

    public TrainingSession() {}

    public TrainingSession(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
}