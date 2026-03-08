package com.grappling.grapplingcoach.domain;

import lombok.Getter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean present;

    public Kid() {
    }

    public Kid(String name) {
        this.name = name;
        this.present = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return present;
    }

    public void markPresent() {
        this.present = true;
    }

    public void markAbsent() {
        this.present = false;
    }
}
