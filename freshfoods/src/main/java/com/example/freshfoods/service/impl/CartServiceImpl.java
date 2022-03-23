package com.example.freshfoods.service.impl;

import com.example.freshfoods.entity.Cart;
import com.example.freshfoods.entity.CartFood;
import com.example.freshfoods.entity.Food;
import com.example.freshfoods.entity.User;
import com.example.freshfoods.exception.ResourceNotFoundException;
import com.example.freshfoods.model.*;
import com.example.freshfoods.repository.*;
import com.example.freshfoods.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartFoodRepository cartFoodRepository;
    private final FoodRepository foodRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository,
                           CartFoodRepository cartFoodRepository, FoodRepository foodRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartFoodRepository = cartFoodRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public CartDTO createNewCart() {
        Cart newCart = new Cart();
        newCart.setUser(getUser());
        Cart savedCart = cartRepository.save(newCart);
        return CartDTO.builder()
                .cartId(savedCart.getId())
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCart(UpdateCartDTO updateCartDTO) {
        Optional<Cart> cartOptional = cartRepository.findById(updateCartDTO.getCartId());
        if(cartOptional.isEmpty()) throw new ResourceNotFoundException("Cart is not exists!");
        Cart cart = cartOptional.get();

        List<Long> foodIds = new ArrayList<>();
        List<CartFood> cartFoods = cart.getFoods();
        for(CartFood cartFood : cartFoods)
            foodIds.add(cartFood.getId());

        List<UpdateFoodDTO> updateFoodDTOS = updateCartDTO.getFoods();

        for(UpdateFoodDTO updateFoodDTO : updateFoodDTOS) {
            Long foodId = updateFoodDTO.getFoodId();
            Integer qty = updateFoodDTO.getQty();

            Optional<Food> foodOptional = foodRepository.findById(foodId);
            if(foodOptional.isEmpty()) throw new ResourceNotFoundException("Unknown food in request!");

            if(foodIds.contains(foodId)) {
                foodIds.remove(foodId);
                Optional<CartFood> cartFood = cartFoodRepository.findCartFoodByCartAndFood(cart, foodOptional.get());
                if(cartFood.isPresent()) {
                    CartFood cartFood1 = cartFood.get();
                    cartFood1.setQty(qty);
                    cartFoodRepository.save(cartFood1);
                    break;
                }
            }

            CartFood cartFood = new CartFood(qty, cart, foodOptional.get());
            cartFoodRepository.save(cartFood);
        }

        for(Long id : foodIds) {
            cartFoodRepository.deleteById(id);
        }
    }

    @Override
    public CartDTO getCart(Long id) {
        Cart cart = cartRepository.getById(id);
        return toCartDTO(cart);
    }

    @Override
    public List<CartDTO> carts() {
        User user = getUser();
        List<Cart> carts = cartRepository.getCartsByUser(user);
        List<CartDTO> cartDTOS = new ArrayList<>();
        for(Cart cart : carts)
            cartDTOS.add(toCartDTO(cart));
        return cartDTOS;
    }

    private CartDTO toCartDTO(Cart cart) {
        Set<CartFoodDTO> cartFoodDTOS = new HashSet<>();
        List<CartFood> cartFoods = cart.getFoods();
        for(CartFood cartFood : cartFoods) {
            Food food = cartFood.getFood();
            ProducerDTO producerDTO = ProducerDTO.builder()
                    .name(food.getProducer().getName())
                    .build();
            FoodDTO foodDTO = FoodDTO.builder()
                    .id(food.getId())
                    .category(food.getCategory().getName())
                    .imageUrl(food.getImageUrl())
                    .name(food.getName())
                    .price(food.getPrice())
                    .producer(producerDTO)
                    .build();
            CartFoodDTO cartFoodDTO = CartFoodDTO.builder()
                    .food(foodDTO)
                    .qty(cartFood.getQty())
                    .build();
            cartFoodDTOS.add(cartFoodDTO);
        }

        return CartDTO.builder()
                .cartId(cart.getId())
                .items(cartFoodDTOS)
                .build();
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails requestedUser = (UserDetails) authentication.getPrincipal();
        System.out.println(requestedUser.getUsername());
        Optional<User> userOptional = userRepository.findUserByUsername(requestedUser.getUsername());
        if(userOptional.isEmpty()) throw new ResourceNotFoundException("User not exists");
        return userOptional.get();
    }
}
