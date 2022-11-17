package ru.javamentor.springmvchibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.springmvchibernate.model.User;
import ru.javamentor.springmvchibernate.service.RoleServiceImpl;
import ru.javamentor.springmvchibernate.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final RoleServiceImpl roleService;
    private final UserServiceImpl userService;

    @Autowired
    public UserController(RoleServiceImpl roleService, UserServiceImpl userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping()
    public String getProfileUser(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
