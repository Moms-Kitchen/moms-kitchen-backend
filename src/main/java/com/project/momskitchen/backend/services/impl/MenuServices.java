package com.project.momskitchen.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.project.momskitchen.backend.dao.impl.MenuDAOimpl;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.services.Services;

import org.springframework.stereotype.Service;

@Service
public class MenuServices implements Services {

    
    MenuDAOimpl md = new MenuDAOimpl();

    public MenuServices() {
    }

    public Menu getMenu(int idMenu) throws MomsPersistenceException{
        Menu menu = md.consultMenu(idMenu);
        return menu;
    }

    public List<Menu> getChefMenus(int idChef) throws MomsPersistenceException{
        List<Menu> menus = md.consultChefMenus(idChef);
        return menus;
    }

    public List<Menu> getMenus() throws MomsPersistenceException{
        List<Menu> menus = md.consultMenus();
        return menus;
    }

    public Boolean setMenu(Menu menu) throws MomsPersistenceException {
        Boolean b = false;
        b = md.createMenu(menu);
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