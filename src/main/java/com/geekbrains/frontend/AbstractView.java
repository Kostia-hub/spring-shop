package com.geekbrains.frontend;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class AbstractView extends VerticalLayout {
    protected TextField initTextFieldWithPlaceholder(String placeholder) {
        TextField textField = new TextField();
        textField.setPlaceholder(placeholder);
        return textField;
    }
}
