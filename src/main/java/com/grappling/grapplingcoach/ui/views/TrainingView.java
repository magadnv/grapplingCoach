package com.grappling.grapplingcoach.ui.views;

import com.grappling.grapplingcoach.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import com.grappling.grapplingcoach.service.AttendanceService;
import com.grappling.grapplingcoach.domain.Attendance;
import com.grappling.grapplingcoach.domain.TrainingSession;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.checkbox.Checkbox;

@Route(value = "training", layout = MainLayout.class)
public class TrainingView extends VerticalLayout {

    private final AttendanceService attendanceService;

    private TrainingSession session;

    private Grid<Attendance> grid = new Grid<>();

    public TrainingView(AttendanceService attendanceService) {

        this.attendanceService = attendanceService;

        Button startTraining = new Button("Start Training");

        startTraining.addClickListener(event -> {

            session = attendanceService.createSession();
            attendanceService.createAttendanceForSession(session);

            loadAttendance();

            startTraining.setEnabled(false);

        });

        grid.addColumn(a -> a.getKid().getName())
                .setHeader("Name");

        grid.addComponentColumn(attendance -> {

            Checkbox checkbox = new Checkbox(attendance.isPresent());

            checkbox.addValueChangeListener(event -> {

                if (event.getValue()) {
                    attendanceService.markPresent(attendance);
                } else {
                    attendanceService.markAbsent(attendance);
                }

            });

            return checkbox;

        }).setHeader("Present");

        add(startTraining, grid);
    }

    private void loadAttendance() {

        if (session == null) {
            return;
        }

        grid.setItems(
                attendanceService.getAttendancesForSession(session)
        );

    }
}
