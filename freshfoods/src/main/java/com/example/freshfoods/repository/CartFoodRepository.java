package com.example.freshfoods.repository;

import com.example.freshfoods.entity.CartFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartFoodRepository extends JpaRepository<CartFood, Long> {
}
