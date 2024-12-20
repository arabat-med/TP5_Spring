package org.springsecurity.tp5.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springsecurity.tp5.model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
