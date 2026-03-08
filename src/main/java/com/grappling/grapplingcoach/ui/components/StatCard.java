package com.grappling.grapplingcoach.ui.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;

public class StatCard extends Div {

    public StatCard(String title, String value) {

        H3 titleElement = new H3(title);
        Span valueElement = new Span(value);

        add(titleElement, valueElement);

        getStyle()
                .set("padding", "20px")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 12px rgba(0,0,0,0.1)")
                .set("text-align", "center")
                .set("min-width", "150px");

        valueElement.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold");
    }
}