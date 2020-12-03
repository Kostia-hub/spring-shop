package com.geekbrains.controllers;

import com.geekbrains.entities.Order;
import com.geekbrains.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders;
    }

}
