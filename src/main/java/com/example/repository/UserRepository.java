package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    List<User> findAllByRoleDescriptionIgnoreCase(String role);

    @Transactional
    void deleteByUserName(String username);
}
