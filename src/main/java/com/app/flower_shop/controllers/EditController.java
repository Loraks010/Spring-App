package com.app.flower_shop.controllers;

import com.app.flower_shop.models.Product;
import com.app.flower_shop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EditController {
    @Autowired
    private ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("edit{product}")
    public String editProductGet(@PathVariable(value="product") long id, Model model) {
        Optional<Product> dbObject=productRepository.findById(id);
        model.addAttribute("result",dbObject.get());
        return "editProduct";
    }
    @PostMapping("edit{product}")
    public String editProductPost(@PathVariable(value="product") long id,@RequestParam String name,
                                 @RequestParam String price,
                                 @RequestParam String description, Model model,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        Optional<Product> dbObject=productRepository.findById(id);
        Product ob=new Product(dbObject.get());
        if (file != null&& !file.isEmpty()) {
            File fileToDelete= new File(uploadPath + "/" +ob.getFilename());
            fileToDelete.delete();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuIdFile = UUID.randomUUID().toString();
            String resultFileName = uuIdFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            ob.setFilename(resultFileName);
        }
        ob.setName(name);
        ob.setPrice(price);
        ob.setDescription(description);
        productRepository.save(ob);

        return "redirect:/assortment";
    }
    @GetMapping("delete{product}")
    public String deleteProductGet(@PathVariable(value="product") long id, Model model) {
        if(productRepository.existsById(id)) {
            File fileToDelete= new File(uploadPath + "/" +productRepository.findById(id).get().getFilename());
            fileToDelete.delete();
            productRepository.deleteById(id);
        }
        return "redirect:/assortment";
    }

}
