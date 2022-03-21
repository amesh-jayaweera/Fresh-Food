package com.example.freshfoods.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FoodDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String category;
    private ProducerDTO producer;
}
