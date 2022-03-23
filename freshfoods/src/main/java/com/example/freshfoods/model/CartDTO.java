package com.example.freshfoods.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CartDTO {
    private Long cartId;
    private Set<CartFoodDTO> items;
}
