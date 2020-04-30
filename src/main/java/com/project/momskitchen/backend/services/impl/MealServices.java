package com.project.momskitchen.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.project.momskitchen.backend.dao.impl.MealDAOimpl;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;
import com.project.momskitchen.backend.services.Services;

import org.springframework.stereotype.Service;

@Service
public class MealServices implements Services {

    
    MealDAOimpl md = new MealDAOimpl();

    public MealServices() {
    }

    public Meal getMeal(int idMeal) throws MomsPersistenceException{
        Meal meal = md.consultMeal(idMeal);
        return meal;
    }

    public List<Meal> getMealsByName(String mealname) throws MomsPersistenceException{        
        return md.consultMealsByName(mealname);
    }

    public List<Meal> getMeals() throws MomsPersistenceException{
        List<Meal> meals = md.consultMeals();
        return meals;
    }

    public Boolean setMeal(Meal meal) throws MomsPersistenceException {
        Boolean b = false;
        b = md.createMeal(meal);
        return b;
    }

    @Override
    public void createElement(Object elemento) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object Read() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object Update() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void Delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public ArrayList<Object> allElements() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getElmentObject(Object obj) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    

}