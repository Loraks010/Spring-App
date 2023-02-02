package com.app.flower_shop.controllers;

import com.app.flower_shop.models.Product;
import com.app.flower_shop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AddProductController {
    @Autowired
    private ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/add")
    public String addGetProduct( Model model) {
        return "flower_shop-add";
    }

    @PostMapping("/add")
    public String addPostProduct(@RequestParam String name, @RequestParam String price,
                                 @RequestParam String description, Model model,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        if(file!=null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists())
            {
                uploadDir.mkdir();
            }
            String uuIdFile = UUID.randomUUID().toString();
            String resultFileName = uuIdFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFileName));
            Product product =new Product(name,price,description,resultFileName);
            productRepository.save(product);
        }
        return "redirect:/assortment";
    }
}
