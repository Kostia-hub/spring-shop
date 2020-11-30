package com.geekbrains.repositories;

import com.geekbrains.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByUserId(long userId);
}
