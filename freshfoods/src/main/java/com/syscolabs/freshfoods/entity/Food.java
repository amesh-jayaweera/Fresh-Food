package com.syscolabs.freshfoods.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    private int qty;
    private double price;
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
}
