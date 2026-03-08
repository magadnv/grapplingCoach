package com.grappling.grapplingcoach.service;

import com.grappling.grapplingcoach.domain.*;
import com.grappling.grapplingcoach.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final KidRepository kidRepository;
    private final TrainingSessionRepository sessionRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(KidRepository kidRepository, TrainingSessionRepository sessionRepository, AttendanceRepository attendanceRepository) {

        this.kidRepository = kidRepository;
        this.sessionRepository = sessionRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public TrainingSession createSession() {
        TrainingSession session = new TrainingSession(LocalDate.now());
        return sessionRepository.save(session);
    }

    public void createAttendanceForSession(TrainingSession session) {

        List<Kid> kids = kidRepository.findAll();

        for (Kid kid : kids) {
            Attendance attendance = new Attendance(kid, session);
            attendanceRepository.save(attendance);
        }
    }

    public void markPresent(Attendance attendance) {
        attendance.markPresent();
        attendanceRepository.save(attendance);
    }

    public void markAbsent(Attendance attendance) {
        attendance.markAbsent();
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendancesForSession(TrainingSession session) {
        return attendanceRepository.findBySession(session);
    }

    public List<TrainingSession> getSessions() {
        return sessionRepository.findAllByOrderByDateDesc();
    }

    public double getAttendanceRate(Kid kid) {

        List<Attendance> attendances = attendanceRepository.findByKid(kid);

        if (attendances.isEmpty()) {
            return 0;
        }

        long presentCount = attendances.stream()
                .filter(Attendance::isPresent)
                .count();

        return (double) presentCount / attendances.size();
    }

    public TrainingSession startTrainingSession() {

        Optional<TrainingSession> existing =
                sessionRepository.findByDate(LocalDate.now());

        if (existing.isPresent()) {
            return existing.get();
        }

        TrainingSession session = new TrainingSession(LocalDate.now());
        sessionRepository.save(session);

        List<Kid> kids = kidRepository.findAll();

        for (Kid kid : kids) {
            attendanceRepository.save(new Attendance(kid, session));
        }

        return session;
    }

    public Optional<TrainingSession> getTodaySession() {
        return sessionRepository.findByDate(LocalDate.now());
    }

    public void deleteTrainingSession(Long sessionId) {

        TrainingSession session =
                sessionRepository.findById(sessionId)
                        .orElseThrow();

        attendanceRepository.deleteAll(
                attendanceRepository.findBySession(session)
        );

        sessionRepository.delete(session);
    }
}
