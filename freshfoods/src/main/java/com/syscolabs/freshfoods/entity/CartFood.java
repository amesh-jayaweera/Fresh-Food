package com.syscolabs.freshfoods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int qty;
    private double price;

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
