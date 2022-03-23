package com.example.freshfoods.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateCartDTO {
    private Long cartId;
    private List<UpdateFoodDTO> foods;
}
