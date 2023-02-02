package com.app.flower_shop.controllers;

import com.app.flower_shop.models.Product;
import com.app.flower_shop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/assortment")
    public String assortmentGet( Model model) {
        Iterable<Product> products=productRepository.findAll();
        model.addAttribute("products",products);
        return "flower_shop-assortment";
    }
}
