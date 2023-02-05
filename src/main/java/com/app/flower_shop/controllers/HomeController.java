package com.app.flower_shop.controllers;

import com.app.flower_shop.models.Product;
import com.app.flower_shop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/assortment")
    public String assortmentGet(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "flower_shop-assortment";
    }

    @PostMapping("filter")
    public String search(@RequestParam String text, Model model) {
        List<Product> products = productRepository.findByDescriptionContaining(text);
        products.addAll(productRepository.findByNameContaining(text));
        products=products.stream().distinct().collect(Collectors.toList());
        model.addAttribute("products",products);
        return "flower_shop-assortment";
    }
}
