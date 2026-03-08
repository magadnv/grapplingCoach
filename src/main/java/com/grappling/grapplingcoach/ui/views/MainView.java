package com.grappling.grapplingcoach.ui.views;

import com.grappling.grapplingcoach.domain.Kid;
import com.grappling.grapplingcoach.service.KidService;
import com.grappling.grapplingcoach.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.checkbox.Checkbox;

@Route(value = "kids", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private final KidService service;

    public MainView(KidService service) {

        this.service = service;

        TextField nameField    = new TextField("Name");

        Grid<Kid> grid = new Grid<>();

        grid.addColumn(Kid::getName)
                .setHeader("Name");
        grid.addComponentColumn(kid -> {

            Checkbox checkbox = new Checkbox(kid.isPresent());

            checkbox.addValueChangeListener(event -> {

                if (event.getValue()) {
                    kid.markPresent();
                } else {
                    kid.markAbsent();
                }

                service.save(kid);
                grid.getDataProvider().refreshItem(kid);

            });

            return checkbox;

        }).setHeader("Anwesen");

        Button addButton       = new Button("Neuer Ninja", event -> {

            service.addKid(nameField.getValue());

            grid.setItems(service.getKids());

            nameField.clear();

        });

        add(nameField, addButton, grid);
    }

}
