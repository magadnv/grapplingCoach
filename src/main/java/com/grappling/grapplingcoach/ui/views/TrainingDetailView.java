package com.grappling.grapplingcoach.ui.views;

import com.grappling.grapplingcoach.domain.Attendance;
import com.grappling.grapplingcoach.domain.TrainingSession;
import com.grappling.grapplingcoach.repository.TrainingSessionRepository;
import com.grappling.grapplingcoach.service.AttendanceService;
import com.grappling.grapplingcoach.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;

@Route(value = "training-detail/:sessionId", layout = MainLayout.class)
public class TrainingDetailView extends VerticalLayout implements BeforeEnterObserver {

    private final AttendanceService attendanceService;
    private final TrainingSessionRepository sessionRepository;

    private Grid<Attendance> grid = new Grid<>();

    public TrainingDetailView(
            AttendanceService attendanceService,
            TrainingSessionRepository sessionRepository) {

        this.attendanceService = attendanceService;
        this.sessionRepository = sessionRepository;

        grid.addColumn(a -> a.getKid().getName())
                .setHeader("Name");

        grid.addColumn(Attendance::isPresent)
                .setHeader("Present");

        add(grid);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        Long sessionId = Long.valueOf(
                event.getRouteParameters()
                        .get("sessionId")
                        .orElseThrow()
        );

        TrainingSession session = sessionRepository
                .findById(sessionId)
                .orElseThrow();

        List<Attendance> attendances = attendanceService.getAttendancesForSession(session);

        grid.setItems(attendances);
    }
}
