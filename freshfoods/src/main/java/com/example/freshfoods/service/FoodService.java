package com.example.freshfoods.service;

import com.example.freshfoods.entity.FoodCategory;
import com.example.freshfoods.model.CreateFoodDTO;
import com.example.freshfoods.model.FoodDTO;

import java.util.List;

public interface FoodService {
    FoodDTO save(CreateFoodDTO food);
    List<FoodDTO> get();
    List<FoodDTO> get(FoodCategory category);
    FoodDTO get(Long id);
    List<FoodDTO> contains(String category, String name);
}
