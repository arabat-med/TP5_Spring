package com.example.TP5.repositery;

import com.example.TP5.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Long> {
    user findByUsername(String username);
}
