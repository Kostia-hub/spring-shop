package com.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "products_categories",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name =  "category_id"))
    private List<Category> categories;

    @Override
    public String toString() {
        return this.title;
    }

}
