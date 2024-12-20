package com.example.TP5.service;

import com.example.TP5.repositery.RoleRepository;
import com.example.TP5.repositery.UserRepository;
import com.example.TP5.entity.user;
import com.example.TP5.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public user createUser(String username, String password, String email, Set<String> roleNames) {
        // Créer les rôles
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName);
            if (role != null) {
                roles.add(role);
            }
        }

        // Créer l'utilisateur
        user user = new user(username, passwordEncoder.encode(password), email);
        user.setRoles(roles);

        // Sauvegarder l'utilisateur
        return userRepository.save(user);
    }
}