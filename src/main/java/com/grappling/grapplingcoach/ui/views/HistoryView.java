package com.grappling.grapplingcoach.ui.views;

import com.grappling.grapplingcoach.domain.TrainingSession;
import com.grappling.grapplingcoach.service.AttendanceService;
import com.grappling.grapplingcoach.ui.MainLayout;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.component.button.Button;

@Route(value = "history", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {

    public HistoryView(AttendanceService attendanceService) {

        Grid<TrainingSession> grid = new Grid<>(TrainingSession.class, false);

        grid.addColumn(TrainingSession::getDate)
                .setHeader("Training Date");

        // Open Training
        grid.addComponentColumn(session -> {

            RouterLink link = new RouterLink(
                    "Open",
                    TrainingDetailView.class,
                    new RouteParameters(
                            "sessionId",
                            session.getId().toString()
                    )
            );

            return link;

        }).setHeader("Details");

        // DELETE TRAINING
        grid.addComponentColumn(session -> {

            Button deleteButton = new Button("Delete", event -> {

                attendanceService.deleteTrainingSession(session.getId());

                grid.setItems(attendanceService.getSessions());

            });

            return deleteButton;

        }).setHeader("Delete");

        grid.setItems(attendanceService.getSessions());

        add(grid);
    }
}