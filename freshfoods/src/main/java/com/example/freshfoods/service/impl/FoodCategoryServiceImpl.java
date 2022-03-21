package com.example.freshfoods.service.impl;

import com.example.freshfoods.entity.FoodCategory;
import com.example.freshfoods.repository.FoodCategoryRepository;
import com.example.freshfoods.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Override
    public List<FoodCategory> get() {
        return foodCategoryRepository.findAll();
    }
}
