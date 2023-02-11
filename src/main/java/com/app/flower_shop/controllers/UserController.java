package com.app.flower_shop.controllers;

import com.app.flower_shop.models.Role;
import com.app.flower_shop.models.User;
import com.app.flower_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("role", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String saveUserEdit(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("{user}/delete")
    public String userDelete(@PathVariable User user, Model model) {
        userService.deleteUser(user);
        return "redirect:/user";
    }

}
