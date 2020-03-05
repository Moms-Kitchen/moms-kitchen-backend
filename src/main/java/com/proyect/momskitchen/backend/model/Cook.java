package com.proyect.momskitchen.backend.model;

import java.util.List;

public class Cook extends User {

    List<Meal> Menus;
    List<String> Specialties;

    public List<Meal> getMenus() {
        return Menus;
    }

    public void setMenus(List<Meal> menus) {
        Menus = menus;
    }

    public List<String> getSpecialties() {
        return Specialties;
    }

    public void setSpecialties(List<String> specialties) {
        Specialties = specialties;
    }
    
}