package com.example.freshfoods.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FoodCategory {

    @Id
    private String name;

    public FoodCategory(String name) {
        this.name = name;
    }

    @OneToMany(
            targetEntity = Food.class,
            mappedBy = "category",
            fetch = FetchType.LAZY
    )
    private List<Food> foods;
}
