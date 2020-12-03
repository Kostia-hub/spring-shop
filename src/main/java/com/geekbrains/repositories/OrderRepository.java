package com.geekbrains.repositories;

import com.geekbrains.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

    @Query("SELECT o FROM Order o JOIN FETCH o.user")
    List<Order> findAllByUserId(long userId);
}
