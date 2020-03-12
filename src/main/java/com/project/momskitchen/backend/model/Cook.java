package com.project.momskitchen.backend.model;

import java.util.List;

public class Cook extends User {

    List<Meal> menus;
    List<String> specialties;

    public List<Meal> getMenus() {
        return menus;
    }

    public void setMenus(List<Meal> lmenus) {
        menus = lmenus;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<String> lspecialties) {
        specialties = lspecialties;
    }
    
}