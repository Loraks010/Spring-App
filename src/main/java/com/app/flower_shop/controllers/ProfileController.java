package com.app.flower_shop.controllers;

import com.app.flower_shop.models.User;
import com.app.flower_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        return "flower_shop-profile";
    }

    @PostMapping("/profile")
    public String changePassword(
            @RequestParam String password,
            Authentication authentication,
            Model model) {
        User user = (User) authentication.getPrincipal();
        user.setPassword(passwordEncoder.encode(password));
        if (userService.changePassword(user)) {
            model.addAttribute("message", "Password changed!");
        } else {
            model.addAttribute("message", "Password not changed!");
        }
        return "flower_shop-profile";
    }
}
