package com.example.freshfoods.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CartFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int qty;

    @ManyToOne(
            targetEntity = Cart.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Cart cart;

    @ManyToOne(
            targetEntity = Food.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Food food;

    public CartFood(int qty, Cart cart, Food food) {
        this.qty = qty;
        this.cart = cart;
        this.food = food;
    }

    public CartFood(long id, int qty, Cart cart, Food food) {
        this.id = id;
        this.qty = qty;
        this.cart = cart;
        this.food = food;
    }
}
