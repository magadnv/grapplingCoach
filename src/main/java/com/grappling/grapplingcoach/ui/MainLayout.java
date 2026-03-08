package com.grappling.grapplingcoach.ui;

import com.grappling.grapplingcoach.ui.views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {

        // Logo
        RouterLink logo = new RouterLink("", DashboardView.class);

        H1 title = new H1("Grappling Coach");
        title.getStyle()
                .set("font-size", "1.4em")
                .set("margin", "0");

        logo.add(title);
        logo.getStyle()
                .set("text-decoration", "none")
                .set("color", "inherit");

        // Navigation Links
        RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class);
        RouterLink kidsLink = new RouterLink("Kinder", MainView.class);
        RouterLink trainingLink = new RouterLink("Training", TrainingView.class);
        RouterLink historyLink = new RouterLink("Verlauf", HistoryView.class);
        RouterLink statisticsLink = new RouterLink("Statistik", StatisticsView.class);

        // Styling für alle Links
        RouterLink[] links = {
                dashboardLink,
                kidsLink,
                trainingLink,
                historyLink,
                statisticsLink
        };

        for (RouterLink link : links) {
            link.getStyle()
                    .set("text-decoration", "none")
                    .set("color", "#333")
                    .set("font-weight", "500")
                    .set("padding", "6px 10px")
                    .set("border-radius", "6px");
        }

        // Menü Layout
        HorizontalLayout menu = new HorizontalLayout(
                logo,
                dashboardLink,
                kidsLink,
                trainingLink,
                historyLink,
                statisticsLink
        );

        menu.setWidthFull();
        menu.setAlignItems(FlexComponent.Alignment.CENTER);
        menu.setSpacing(true);

        menu.getStyle()
                .set("padding", "0 40px")
                .set("height", "64px")
                .set("border-bottom", "1px solid #eee");

        // Logo links, Navigation rechts
        menu.expand(logo);

        addToNavbar(menu);
    }
}