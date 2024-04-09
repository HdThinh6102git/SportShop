package com.example.sportshopapp.repositories;

import com.example.sportshopapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean findUserById(Long userId);
}
