package com.example.freshfoods.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class CreateFoodDTO {
    private Long id;
    @NotEmpty(message = "{NotEmpty.foodName}")
    private String name;
    private BigDecimal price;
    @NotEmpty(message = "{NotEmpty.imageUrl}")
    private String imageUrl;
    @NotNull(message = "{NotNull.foodCategory}")
    private String category;
    @NotNull(message = "{NotNull.producer}")
    private Long producerId;
}
