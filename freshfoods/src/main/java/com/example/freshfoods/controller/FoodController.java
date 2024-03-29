package com.example.freshfoods.controller;

import com.example.freshfoods.entity.FoodCategory;
import com.example.freshfoods.model.ResponseDTO;
import com.example.freshfoods.service.FoodCategoryService;
import com.example.freshfoods.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/v1/food")
public class FoodController {

    private final FoodService foodService;
    private final FoodCategoryService foodCategoryService;


    @Autowired
    public FoodController(FoodService foodService, FoodCategoryService foodCategoryService) {
        this.foodService = foodService;
        this.foodCategoryService = foodCategoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<?>> findAll() {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(foodService.get()).build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> findById(@NotNull(message = "{NotNull.foodId}") @PathVariable Long id) {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(foodService.get(id)).build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/category")
    public ResponseEntity<ResponseDTO<?>> findByCategory(@NotNull(message = "{NotNull.category}") @RequestParam("category")
                                                                     String category) {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(foodService.get(new FoodCategory(category))).build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<?>> searchByName(@RequestParam("category") String category,
                                                       @NotEmpty(message = "{NotEmpty.foodName}")
                                                       @RequestParam("name") String name) {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(foodService.contains(category, name)).build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/category/list")
    public ResponseEntity<ResponseDTO<?>> getCategories() {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(foodCategoryService.get()).build();

        return ResponseEntity.ok(responseDTO);
    }
}
