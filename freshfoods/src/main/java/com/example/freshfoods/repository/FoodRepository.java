package com.example.freshfoods.repository;

import com.example.freshfoods.entity.Food;
import com.example.freshfoods.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByCategory(FoodCategory category);
    List<Food> findAllByNameContains(String name);
    List<Food> findAllByCategoryAndNameContains(FoodCategory category, String name);
}
