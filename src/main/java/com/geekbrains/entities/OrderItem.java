package com.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name =  "items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Product product;

    private BigDecimal price;
    private int quantity;

    public OrderItem() {}

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = new BigDecimal(0).add(product.getPrice());
    }

    public void increment() {
        this.quantity++;
        this.price = new BigDecimal(this.quantity * product.getPrice().doubleValue());
    }

    public void decrement() {
        this.quantity--;
        this.price = new BigDecimal(this.quantity * product.getPrice().doubleValue());
    }

}
