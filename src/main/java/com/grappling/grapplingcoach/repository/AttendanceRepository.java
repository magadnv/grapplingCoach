package com.grappling.grapplingcoach.repository;

import com.grappling.grapplingcoach.domain.Attendance;
import com.grappling.grapplingcoach.domain.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import com.grappling.grapplingcoach.domain.TrainingSession;


public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findBySession(TrainingSession session);
    List<Attendance> findByKid(Kid kid);
    long countBySessionDateAndPresentTrue(LocalDate date);

}
