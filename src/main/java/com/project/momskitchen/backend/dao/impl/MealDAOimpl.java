package com.project.momskitchen.backend.dao.impl;

import java.util.List;

import com.project.momskitchen.backend.dao.MealDao;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;
import com.project.momskitchen.backend.persistence.impl.MomsKitchenDB;

public class MealDAOimpl implements MealDao{
    private MomsKitchenDB bg = new MomsKitchenDB();

	@Override
	public List<Meal> consultMeals() throws MomsPersistenceException {
		try {
            return bg.getMeals();
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public Meal consultMeal(int id) throws MomsPersistenceException {
		try {
            return bg.getMeal(id);
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
	}

	
	@Override
    public Boolean createMeal(Meal meal) throws MomsPersistenceException{
        Boolean b = false;
        try {
            b = bg.createMeal(meal);
            return b; 
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return b;
        }
	}

	@Override
	public void insertarMeal(Meal meal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrder(Meal meal) {
		// TODO Auto-generated method stub
		
	}
}