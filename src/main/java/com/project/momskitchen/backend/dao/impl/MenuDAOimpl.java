package com.project.momskitchen.backend.dao.impl;

import java.util.List;

import com.project.momskitchen.backend.dao.MenuDao;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;
import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.persistence.impl.MomsKitchenDB;


public class MenuDAOimpl implements MenuDao {

    private MomsKitchenDB bg = new MomsKitchenDB();

    @Override
    public Menu consultMenu() throws MomsPersistenceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Menu consultMenu(int id) throws MomsPersistenceException{
        try {
            return bg.getMenu(id);
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
    }

    public List<Menu> consultChefMenus(int id) throws MomsPersistenceException{
        try {
            return bg.getUserMenu(id);
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
    }

    public List<Menu> consultMenus() throws MomsPersistenceException{
        try {
            return bg.getMenus();
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean createMenu(Menu menu) throws MomsPersistenceException{
        Boolean b = false;
        try {
            b = bg.createMenu(menu);
            return b; 
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return b;
        }
    }

    @Override
    public void insertarAMenu(Meal meals) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateMenu(Menu menu) {
        // TODO Auto-generated method stub

    }
}