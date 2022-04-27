package com.example.freshfoods.service.impl;

import com.example.freshfoods.entity.Food;
import com.example.freshfoods.entity.FoodCategory;
import com.example.freshfoods.exception.BadRequestException;
import com.example.freshfoods.exception.ResourceNotFoundException;
import com.example.freshfoods.model.CreateFoodDTO;
import com.example.freshfoods.model.FoodDTO;
import com.example.freshfoods.model.ProducerDTO;
import com.example.freshfoods.repository.FoodCategoryRepository;
import com.example.freshfoods.repository.FoodRepository;
import com.example.freshfoods.repository.ProducerRepository;
import com.example.freshfoods.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class FoodServiceImpl implements FoodService {

    private final ProducerRepository producerRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(ProducerRepository producerRepository, FoodCategoryRepository foodCategoryRepository,
                           FoodRepository foodRepository) {
        this.producerRepository = producerRepository;
        this.foodRepository = foodRepository;
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public FoodDTO save(CreateFoodDTO food) {
        Food newFood = new Food(food.getName().trim(), food.getPrice(), food.getImageUrl());
        newFood.setCategory(foodCategoryRepository.findById(food.getCategory().trim()).orElseThrow(
                ()-> new BadRequestException("Food category not exists")
        ));
        newFood.setProducer(producerRepository.findById(food.getProducerId()).orElseThrow(
                ()-> new BadRequestException("Producer is not exists")
        ));
        Food savedFood = foodRepository.save(newFood);
        ProducerDTO producerDTO = ProducerDTO.builder()
                .name(savedFood.getProducer().getName()).build();
        return FoodDTO.builder()
                .id(savedFood.getId())
                .name(savedFood.getName())
                .price(savedFood.getPrice())
                .imageUrl(savedFood.getImageUrl())
                .category(savedFood.getCategory().getName())
                .producer(producerDTO).build();
    }

    @Override
    public List<FoodDTO> get() {
        List<Food> foods = foodRepository.findAll();
        return toDto(foods);
    }

    @Override
    public List<FoodDTO> get(FoodCategory category) {
        if(foodCategoryRepository.findFoodCategoryByName(category.getName()).isPresent())
            return toDto(foodRepository.findAllByCategory(category));
        throw new BadRequestException("The given food category doesn't exists!");
    }

    @Override
    public FoodDTO get(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()) throw new ResourceNotFoundException(String.format("Food : %s does not exists!", id));
        Food food1 = food.get();

        ProducerDTO producerDTO = ProducerDTO.builder()
                .name(food1.getProducer().getName())
                .contactNo(food1.getProducer().getContactNo()).build();

        return FoodDTO.builder()
                .id(food1.getId())
                .name(food1.getName())
                .price(food1.getPrice())
                .imageUrl(food1.getImageUrl())
                .category(food1.getCategory().getName())
                .producer(producerDTO).build();
    }

    @Override
    public List<FoodDTO> contains(String category, String name) {
        if(category.trim().isEmpty())
            return toDto(foodRepository.findAllByNameContains(name));
        else {
            Optional<FoodCategory> foodCategoryOptional = foodCategoryRepository.findFoodCategoryByName(category);
            if(foodCategoryOptional.isPresent()) {
                FoodCategory foodCategory = foodCategoryOptional.get();
                return toDto(foodRepository.findAllByCategoryAndNameContains(foodCategory, name.trim()));
            }
        }
        return new ArrayList<>();
    }

    private List<FoodDTO> toDto(List<Food> foods) {
        List<FoodDTO> foodDTOS = new ArrayList<>(foods.size());
        for(Food food : foods) {
            ProducerDTO producerDTO = ProducerDTO.builder()
                    .name(food.getProducer().getName())
                    .contactNo(food.getProducer().getContactNo()).build();

            FoodDTO foodDTO = FoodDTO.builder()
                    .id(food.getId())
                    .name(food.getName())
                    .price(food.getPrice())
                    .imageUrl(food.getImageUrl())
                    .category(food.getCategory().getName())
                    .producer(producerDTO).build();

            foodDTOS.add(foodDTO);
        }
        return foodDTOS;
    }
}
