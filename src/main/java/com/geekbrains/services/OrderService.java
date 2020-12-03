package com.geekbrains.services;

import com.geekbrains.entities.Order;
import com.geekbrains.entities.OrderItem;
import com.geekbrains.entities.User;
import com.geekbrains.repositories.OrderItemRepository;
import com.geekbrains.repositories.OrderRepository;
import io.swagger.models.apideclaration.Items;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.geekbrains.entities.Order.Status.MANAGING;

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
        User user = userService.findById(1L);

        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhone());
        order.setUser(user);
        order.setPrice(cartService.getPrice());
        order.setStatus(MANAGING);
        order.setPhoneNumber(user.getPhone());

        final Order savedOrder = orderRepository.save(order);
    }

    @Transactional
    public List<Order> getByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
