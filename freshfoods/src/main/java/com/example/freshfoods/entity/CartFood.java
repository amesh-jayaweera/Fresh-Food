package com.example.freshfoods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class CartFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int qty;

    private BigDecimal price;

    @ManyToOne(
            targetEntity = Cart.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Cart cart;

    @ManyToOne(
            targetEntity = Food.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Food food;
}
