package com.project.momskitchen.backend.model;

import java.math.BigDecimal;
import java.util.List;

public class Menu {
     
    int id;
    int idUser;
    List<Meal> meals;
    BigDecimal price;

    public Menu(int id, int idUser, BigDecimal price) {
        this.id = id;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", idUser=" + idUser + ", meals=" + meals + ", price=" + price + "]";
    }
    
}