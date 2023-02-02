package com.app.flower_shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String filename;
    private String price;
    private String description;

    public Product()
    {}
    public Product(Product ob)
    {
        this.id= ob.getId();
        this.name = ob.getName();
        this.filename = ob.getFilename();
        this.price = ob.getPrice();
        this.description = ob.getDescription();
    }
    public Product( String name,  String price, String description,String filename) {
        this.name = name;
        this.filename = filename;
        this.price = price;
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
