package com.grappling.grapplingcoach.ui.views;

import com.grappling.grapplingcoach.domain.Kid;
import com.grappling.grapplingcoach.repository.KidRepository;
import com.grappling.grapplingcoach.service.AttendanceService;
import com.grappling.grapplingcoach.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "statistics", layout = MainLayout.class)
public class StatisticsView extends VerticalLayout {

    public StatisticsView(
            KidRepository kidRepository,
            AttendanceService attendanceService) {

        Grid<Kid> grid = new Grid<>(Kid.class, false);

        grid.addColumn(Kid::getName)
                .setHeader("Name");

        grid.addColumn(kid ->
                        Math.round(attendanceService.getAttendanceRate(kid) * 100) + "%")
                .setHeader("Attendance");

        grid.setItems(kidRepository.findAll());

        add(grid);
    }
}