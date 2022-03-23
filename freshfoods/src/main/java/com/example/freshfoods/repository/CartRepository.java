package com.example.freshfoods.repository;

import com.example.freshfoods.entity.Cart;
import com.example.freshfoods.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> getCartsByUser(User user);
}
