package com.grappling.grapplingcoach.repository;

import com.grappling.grapplingcoach.domain.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {

    List<TrainingSession> findAllByOrderByDateDesc();
    Optional<TrainingSession> findByDate(LocalDate date);

}