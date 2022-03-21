package com.example.freshfoods.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal price;

    @ManyToOne(
            targetEntity = User.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private User user;

    @OneToMany(
            targetEntity = CartFood.class,
            mappedBy = "cart",
            fetch = FetchType.EAGER
    )
    private List<CartFood> foods;
}
