package org.springsecurity.tp5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springsecurity.tp5.model.Role;
import org.springsecurity.tp5.model.User;
import org.springsecurity.tp5.repos.RoleRepository;
import org.springsecurity.tp5.repos.UserRepository;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Crée des rôles
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            // Crée un utilisateur admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@test.com");
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            // Crée un utilisateur simple
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setEmail("user@test.com");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);

            System.out.println("Données initialisées : utilisateurs et rôles créés.");
        };
    }
}

