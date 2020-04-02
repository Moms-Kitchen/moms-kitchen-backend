package com.project.momskitchen.backend.model;

import java.math.BigDecimal;
import java.util.List;

public class Menu {
     
    int id;
    User chef;
    List<Meal> meals;
    BigDecimal price;

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

    @Override
    public String toString() {
        return "Menu [chef=" + chef + ", id=" + id + ", meals=" + meals + ", price=" + price + "]";
    }

}