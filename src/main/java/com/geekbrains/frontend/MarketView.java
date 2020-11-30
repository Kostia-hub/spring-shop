package com.geekbrains.frontend;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductRepository;
import com.geekbrains.services.CartService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Set;

@Route("market")
public class MarketView extends AbstractView {

    private final CartService cartService;
    private final ProductRepository productRepository;

    public MarketView(CartService cartService,
                      ProductRepository productRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
        initMarketPage();
    }

    private void initMarketPage() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(new Button("Главная", e -> System.out.println("Главная")));
        horizontalLayout.add(new Button("Корзина", e -> UI.getCurrent().navigate("cart")));
        horizontalLayout.add(new Button("Мои заказы", e -> UI.getCurrent().navigate("orders")));

        horizontalLayout.add(initTextFieldWithPlaceholder("Номер телефона"));
        horizontalLayout.add(initTextFieldWithPlaceholder("Пароль"));
        horizontalLayout.add(new Button("Войти", e -> System.out.println("Войти")));

        Grid<Product> productGrid = new Grid<>(Product.class);
        productGrid.setWidth("60%");
        productGrid.setColumns("id", "title", "price");
        productGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        List<Product> productList = productRepository.findAll();
        productGrid.setItems(productList);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        setHorizontalComponentAlignment(Alignment.CENTER, productGrid);
        add(horizontalLayout);
        add(productGrid);

        add(new Button("Добавить выбранные товары", e -> {
            Set<Product> productSet = productGrid.getSelectedItems();
            productSet.stream().forEach(cartService::add);
            Notification.show("Товар успешно добавлен в корзину");
        }));
    }

}
