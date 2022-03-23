package com.example.freshfoods.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartFoodDTO {
    private FoodDTO food;
    private Integer qty;
}
