package com.example.freshfoods.controller;

import com.example.freshfoods.model.CreateFoodDTO;
import com.example.freshfoods.model.ResponseDTO;
import com.example.freshfoods.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/v1/admin/food")
public class AdminFoodController {
    private final FoodService foodService;

    @Autowired
    public AdminFoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody CreateFoodDTO food) {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.toString())
                .body(foodService.save(food)).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
