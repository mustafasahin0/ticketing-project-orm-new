package com.example.repository;

import com.example.entity.Role;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Derived Query, @Query( JPA, NATIVE)

}
