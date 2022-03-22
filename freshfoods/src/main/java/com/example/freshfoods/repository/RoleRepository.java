package com.example.freshfoods.repository;

import com.example.freshfoods.entity.ERole;
import com.example.freshfoods.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(ERole name);
    Boolean existsByRole(ERole name);
}
