package ru.mts.hw_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.hw_3.entity.ERole;
import ru.mts.hw_3.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(ERole role);
}