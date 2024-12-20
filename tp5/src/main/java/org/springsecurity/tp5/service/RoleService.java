package org.springsecurity.tp5.service;

import org.springframework.stereotype.Service;
import org.springsecurity.tp5.model.Role;
import org.springsecurity.tp5.repos.RoleRepository;

import java.util.List;

@Service
public class  RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> findAllRoles() {
        return roleRepository.findAll(); // Retourne tous les rôles
    }


    public Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé avec l'ID : " + id));
    }


    public Role saveRole(Role role) {
        return roleRepository.save(role); // Sauvegarde le rôle
    }


    public void deleteRole(Long id) {
        roleRepository.deleteById(id); // Supprime un rôle par son ID
    }
}
