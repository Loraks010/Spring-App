package com.app.flower_shop.repos;

import com.app.flower_shop.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByDescriptionContaining(String text);

    List<Product> findByNameContaining(String text);
}
