package com.grappling.grapplingcoach.ui;

import com.grappling.grapplingcoach.ui.views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {

        H1 title = new H1("Grappling Coach");

        RouterLink kidsLink = new RouterLink("Kids", MainView.class);
        RouterLink trainingLink = new RouterLink("Training", TrainingView.class);
        RouterLink historyLink = new RouterLink("History", HistoryView.class);
        RouterLink statisticsLink = new RouterLink("Statistics", StatisticsView.class);
        RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class);

        HorizontalLayout menu = new HorizontalLayout();
        menu.setWidthFull();

        menu.add(title, dashboardLink, kidsLink, trainingLink, historyLink, statisticsLink);
        menu.expand(title);

        addToNavbar(menu);
    }
}
