package com.app.flower_shop.controllers;

import com.app.flower_shop.models.Role;
import com.app.flower_shop.models.User;
import com.app.flower_shop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String newUserRegistration(User user, Model model)
    {
        User dbUser =userRepository.findByUsername(user.getUsername());
        if(dbUser!=null)
        {
            model.addAttribute("message","User exists");
            return "registration";
        }
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

}
