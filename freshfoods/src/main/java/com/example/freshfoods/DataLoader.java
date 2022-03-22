package com.example.freshfoods;

import com.example.freshfoods.entity.ERole;
import com.example.freshfoods.entity.Role;
import com.example.freshfoods.entity.User;
import com.example.freshfoods.repository.RoleRepository;
import com.example.freshfoods.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {

        if(!roleRepository.existsByRole(ERole.ROLE_ADMIN)) {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }

        if(!roleRepository.existsByRole(ERole.ROLE_USER)) {
            roleRepository.save(new Role(ERole.ROLE_USER));
        }

        String adminUsername = "admin";
        if(!userRepository.existsByUsername(adminUsername)) {
            String adminPassword = "Test123#";
            User user = new User("Admin", adminUsername, passwordEncoder.encode(adminPassword));
            user.setId(100L);
            Role userRole = roleRepository.findByRole(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            user.setRole(userRole);
            userRepository.save(user);
        }
    }
}
