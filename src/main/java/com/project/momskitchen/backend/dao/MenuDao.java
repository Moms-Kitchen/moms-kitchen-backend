package com.project.momskitchen.backend.dao;

import com.project.momskitchen.backend.model.Menu;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;

public interface MenuDao{

    public Menu consultMenu () throws MomsPersistenceException;
    public Menu consultMenu(int id) throws MomsPersistenceException;
    public void insertarMenu(Meal meals);
    public void updateMenu(Menu menu);
}