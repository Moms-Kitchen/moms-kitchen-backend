package com.project.momskitchen.backend.dao;

import java.util.List;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;

public interface MealDao {
    public List<Meal> consultMeals() throws MomsPersistenceException;
    public Meal consultMeal(int id) throws MomsPersistenceException;
    public Boolean createMeal(Meal meal) throws MomsPersistenceException;
    public void insertarMeal(Meal meal);
    public void updateOrder(Meal meal);

}