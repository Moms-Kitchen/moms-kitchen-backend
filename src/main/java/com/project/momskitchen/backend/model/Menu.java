package com.project.momskitchen.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Menu implements Serializable{     
   
    private static final long serialVersionUID = 1L;
    int id;
    User chef;
    List<Meal> meals;
    BigDecimal price;
    String description;
    String name;

    public Menu(){
        super();
    }

    public Menu(int id, User chef, BigDecimal price) {
        this.id = id;
        this.chef = chef;
        this.price = price;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> lmeals) {
        meals = lmeals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getChef() {
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Menu [chef=" + chef + ", name=" + name + ", description=" + description + ", id=" + id + ", meals=" + meals + ", price="
                + price + "]";
    }



}