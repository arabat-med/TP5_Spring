package org.springsecurity.tp5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springsecurity.tp5.model.Role;
import org.springsecurity.tp5.model.User;
import org.springsecurity.tp5.repos.RoleRepository;
import org.springsecurity.tp5.repos.UserRepository;
import org.springsecurity.tp5.service.RoleService;
import org.springsecurity.tp5.service.UserService;


import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private  RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // Afficher tous les utilisateurs
    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String showAddUserForm(Model model) {
        User user = new User();
        List<Role> availableRoles = roleService.findAllRoles(); // Fetch roles dynamically
        model.addAttribute("user", user);
        model.addAttribute("availableRoles", availableRoles);
        return "add-user";
    }

    @PostMapping("/admin/add")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult result,
                          @RequestParam("selectedRoles") Set<String> selectedRoles,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("availableRoles", roleService.findAllRoles());
            return "add-user";
        }
        // Role mapping logic
        Set<Role> roles = selectedRoles.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }




    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id); // Récupère l'utilisateur à modifier
        List<Role> roles = roleService.findAllRoles(); // Récupère tous les rôles

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);

        return "edit-user"; // Nom de votre template Thymeleaf
    }

    @PostMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id,
                           @ModelAttribute User user,
                           @RequestParam("selectedRoles") Set<String> selectedRoles) {

        Set<Role> roles = selectedRoles.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }


    // Supprimer un utilisateur
    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
