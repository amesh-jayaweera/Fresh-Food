package com.example.freshfoods.repository;

import com.example.freshfoods.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, String> {
    Optional<FoodCategory> findFoodCategoryByName(String name);
}
