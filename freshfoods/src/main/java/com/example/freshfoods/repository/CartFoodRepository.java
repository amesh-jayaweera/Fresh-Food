package com.example.freshfoods.repository;

import com.example.freshfoods.entity.Cart;
import com.example.freshfoods.entity.CartFood;
import com.example.freshfoods.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartFoodRepository extends JpaRepository<CartFood, Long> {
    Optional<CartFood> findCartFoodByCartAndFood(Cart cart, Food food);
}
