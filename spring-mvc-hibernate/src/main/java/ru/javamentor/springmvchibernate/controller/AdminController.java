package ru.javamentor.springmvchibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springmvchibernate.model.User;
import ru.javamentor.springmvchibernate.service.RoleServiceImpl;
import ru.javamentor.springmvchibernate.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("admin", userService.loadUserByUsername(principal.getName()));
        List<User> users = userService.findAll();
        model.addAttribute("newUser", new User());
        model.addAttribute("users", users);
        model.addAttribute("roles", roleService.getAllRole());
        return "admin";
    }

    @GetMapping("/new")
    public String getUserFormForCreate(Model model, Principal principal) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("admin", userService.loadUserByUsername(principal.getName()));
        return "new";
    }

    @PostMapping()
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public void getFormForEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userEdit", userService.findById(id));
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("role", roleService.getAllRole());
    }

    @PostMapping("/{id}")
    public String updateEditUser(@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {
        user.setRoles(roleService.findRolesByName(role));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
