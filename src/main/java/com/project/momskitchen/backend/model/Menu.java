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

}