package com.grappling.grapplingcoach.domain;

import jakarta.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Kid kid;

    @ManyToOne
    private TrainingSession session;

    private boolean present;

    public Attendance() {}

    public Attendance(Kid kid, TrainingSession session) {
        this.kid = kid;
        this.session = session;
        this.present = false;
    }

    public void markPresent() {
        this.present = true;
    }

    public void markAbsent() {
        this.present = false;
    }

    public boolean isPresent() {
        return present;
    }

    public Kid getKid() {
        return kid;
    }

    public TrainingSession getSession() {
        return session;
    }
}
