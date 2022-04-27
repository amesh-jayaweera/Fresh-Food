package com.example.freshfoods.service;

import com.example.freshfoods.model.SignupRequest;

public interface UserService {
    void signUp(SignupRequest signupRequest);
    void updateProfile(SignupRequest signupRequest);
    SignupRequest getUser(Long id);
}
