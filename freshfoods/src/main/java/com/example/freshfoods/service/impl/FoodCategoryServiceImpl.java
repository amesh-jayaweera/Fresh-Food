package com.example.freshfoods.service.impl;

import com.example.freshfoods.entity.FoodCategory;
import com.example.freshfoods.repository.FoodCategoryRepository;
import com.example.freshfoods.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Override
    public Set<String> get() {
        Set<String> categories = new HashSet<>();
        for(FoodCategory foodCategory : foodCategoryRepository.findAll())
            categories.add(foodCategory.getName());
        return categories;
    }
}
