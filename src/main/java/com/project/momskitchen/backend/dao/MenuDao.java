package com.project.momskitchen.backend.dao;

import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.model.Meal;

public interface MenuDao{

    public Menu consultMenu () throws MomsPersistenceException;
    void insertarMenu(Meal meals);
    void updateMenu(Menu menu);
}