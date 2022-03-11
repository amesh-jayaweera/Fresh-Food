package com.example.freshfoods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
