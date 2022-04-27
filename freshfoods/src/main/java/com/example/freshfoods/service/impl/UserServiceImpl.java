package com.example.freshfoods.service.impl;

import com.example.freshfoods.entity.ERole;
import com.example.freshfoods.entity.Role;
import com.example.freshfoods.entity.User;
import com.example.freshfoods.exception.BadRequestException;
import com.example.freshfoods.model.SignupRequest;
import com.example.freshfoods.repository.RoleRepository;
import com.example.freshfoods.repository.UserRepository;
import com.example.freshfoods.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(SignupRequest signupRequest) {
        Optional<User> userOptional = userRepository.findUserByUsername(signupRequest.getUsername());
        if(userOptional.isPresent()) throw new BadRequestException("Username already exists!");
        User user = new User(signupRequest.getName(), signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role is not found."));
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public void updateProfile(SignupRequest signupRequest) {
        Optional<User> userOptional = userRepository.findById(signupRequest.getId());
        if(userOptional.isEmpty()) throw new BadRequestException("User not exists!");
        User user = userOptional.get();
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role is not found."));
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public SignupRequest getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) throw new BadRequestException("User not exists!");
        User user = userOptional.get();
        return new SignupRequest(user.getId(), user.getUsername(), user.getName(), user.getPassword());
    }
}
