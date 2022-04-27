package com.example.freshfoods.controller;

import com.example.freshfoods.model.ResponseDTO;
import com.example.freshfoods.model.SignupRequest;
import com.example.freshfoods.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<?>> update(@Valid @RequestBody SignupRequest signupRequest) {
        userService.updateProfile(signupRequest);
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body("Your account has been updated successfully!").build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> get(@PathVariable Long id) {
        ResponseDTO<?> responseDTO = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.toString())
                .body(userService.getUser(id)).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
