package com.project.momskitchen.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Meal implements Serializable{
    
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    String description;
    BigDecimal price;

    public Meal(){
        super();
    }

    public Meal(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Meal(int id, String name, BigDecimal price,String descripcion) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = descripcion;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Meal [description=" + description + ", id=" + id + ", name=" + name + ", price=" + price + "]";
    }

    

}