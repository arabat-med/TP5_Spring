package org.springsecurity.tp5.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springsecurity.tp5.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}

