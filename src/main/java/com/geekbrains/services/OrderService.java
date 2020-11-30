package com.geekbrains.services;

import com.geekbrains.entities.Order;
import com.geekbrains.entities.OrderItem;
import com.geekbrains.repositories.OrderItemRepository;
import com.geekbrains.repositories.OrderRepository;
import io.swagger.models.apideclaration.Items;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserService userService,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder() {
        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhone());
        order.setUser(userService.findById(1L));
        order.setPrice(cartService.getPrice());

        final Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = order.getItems().stream().peek(orderItem -> orderItem.setOrder(savedOrder)).collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);
    }

    public List<Order> getByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
