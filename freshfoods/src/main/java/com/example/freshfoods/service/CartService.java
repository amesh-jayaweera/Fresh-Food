package com.example.freshfoods.service;

import com.example.freshfoods.model.CartDTO;
import com.example.freshfoods.model.UpdateCartDTO;

import java.util.List;

public interface CartService {
    CartDTO createNewCart();
    void deleteCart(Long id);
    void updateCart(UpdateCartDTO updateCartDTO);
    CartDTO getCart(Long id);
    List<CartDTO> carts();
}
