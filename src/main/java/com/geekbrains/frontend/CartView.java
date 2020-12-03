package com.geekbrains.frontend;

import com.geekbrains.entities.OrderItem;
import com.geekbrains.services.CartService;
import com.geekbrains.services.OrderService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("cart")
public class CartView extends AbstractView {

    private final CartService cartService;
    private final OrderService orderService;

    public CartView(CartService cartService,
                    OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        initCartPage();
    }

    private void initCartPage() {
        Grid<OrderItem> grid = new Grid<>(OrderItem.class);
        grid.setItems(cartService.getItems());
        grid.setWidth("60%");
        grid.setColumns("product", "price", "quantity");

        grid.addColumn(new ComponentRenderer<>(item -> {
            Button plusButton = new Button("+", i -> {
                item.increment();
                grid.setItems(cartService.getItems());
            });

            Button minusButton = new Button("-", i -> {
                item.decrement();
                grid.setItems(cartService.getItems());
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));

        TextField addressField = initTextFieldWithPlaceholder("Введите адрес доставки");
        TextField phoneField = initTextFieldWithPlaceholder("Введите номер телефона");

        Button toOrderButton = new Button("Создать заказ", e -> {
            cartService.setAddress(addressField.getValue());
            cartService.setPhone(phoneField.getValue());
            orderService.saveOrder();

            cartService.clear();
            UI.getCurrent().navigate("market");

            Notification.show("Заказ успешно сохранён и передан менеджеру");
        });

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(grid, addressField, phoneField, toOrderButton);
    }

}
