package com.grappling.grapplingcoach.ui.views;

import com.grappling.grapplingcoach.repository.KidRepository;
import com.grappling.grapplingcoach.repository.AttendanceRepository;
import com.grappling.grapplingcoach.ui.MainLayout;
import com.grappling.grapplingcoach.service.AttendanceService;
import com.grappling.grapplingcoach.domain.TrainingSession;

import com.grappling.grapplingcoach.ui.components.StatCard;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Optional;

@Route(value = "", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView(
            KidRepository kidRepository,
            AttendanceRepository attendanceRepository,
            AttendanceService attendanceService) {

        H2 title = new H2("Coach Dashboard");

        setPadding(false);
        setSpacing(true);

        getStyle().set("padding", "40px");

        // 1. Daten laden
        long kidsCount = kidRepository.count();
        long attendanceCount = attendanceRepository.count();
        long todayPresent = attendanceRepository
                .countBySessionDateAndPresentTrue(LocalDate.now());
        Optional<TrainingSession> todaySession =
                attendanceService.getTodaySession();

        double attendanceRate = 0;

        if (attendanceCount > 0) {
            attendanceRate = (double) todayPresent / kidsCount;
        }

        // 2. Karten bauen
        StatCard kidsCard = new StatCard("Kinder", String.valueOf(kidsCount));
        StatCard attendanceCard =
                new StatCard("Anwesenheit", Math.round(attendanceRate * 100) + "%");
        StatCard todayCard =
                new StatCard("Heute da", String.valueOf(todayPresent));

        Div trainingCard = new Div();

        Button startTraining;

        if (todaySession.isPresent()) {

            startTraining = new Button("Training heute", event -> {

                event.getSource()
                        .getUI()
                        .ifPresent(ui ->
                                ui.navigate(
                                        "training-detail/" + todaySession.get().getId()
                                ));

            });

        } else {

             startTraining = new Button("Start Training", event -> {

                event.getSource()
                        .getUI()
                        .ifPresent(ui -> ui.navigate("training"));

            });

        }

        trainingCard.add(
                new H3("Trainingstag"),
                startTraining
        );
        trainingCard.getStyle()
                .set("padding", "20px")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 12px rgba(0,0,0,0.1)")
                .set("text-align", "center")
                .set("min-width", "180px");

        // 3. Karten nebeneinander
        HorizontalLayout cards = new HorizontalLayout(
                kidsCard,
                attendanceCard,
                todayCard,
                trainingCard
        );

        cards.setPadding(true);
        cards.setWidthFull();
        cards.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        cards.setSpacing(true);
        cards.getStyle().set("gap", "40px");

        // 4. Layout zusammenbauen
        add(title, cards);
    }
}