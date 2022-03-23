package com.example.freshfoods.controller;

import com.example.freshfoods.model.ResponseDTO;
import com.example.freshfoods.model.UpdateCartDTO;
import com.example.freshfoods.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<?>> create() {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.toString())
                .body(cartService.createNewCart()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> delete(@NotNull(message = "{NotNull.cartId}") @PathVariable Long id) {

        cartService.deleteCart(id);

        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body("Cart has been deleted successfully!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<?>> update(@NotNull @RequestBody UpdateCartDTO updateCartDTO) {

        cartService.updateCart(updateCartDTO);

        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body("Cart has been updated successfully!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> get(@NotNull(message = "{NotNull.cartId}") @PathVariable Long id) {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(cartService.getCart(id)).build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<?>> getAll() {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(cartService.carts()).build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
