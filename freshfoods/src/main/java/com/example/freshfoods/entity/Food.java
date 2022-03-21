package com.example.freshfoods.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private BigDecimal price;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(
            targetEntity = FoodCategory.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    private FoodCategory category;

    @ManyToOne(
            targetEntity = Producer.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    private Producer producer;

    public Food(String name, BigDecimal price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
