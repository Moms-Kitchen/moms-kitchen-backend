package com.project.momskitchen.backend.dao;

import com.project.momskitchen.backend.model.Order;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Menu;

public interface OrderDao{

    public Order consultOrder () throws MomsPersistenceException;
    public Order consultOrder(int id) throws MomsPersistenceException;
    public Boolean createOrder(Order Order) throws MomsPersistenceException;
    public void insertarMenu(Menu menus);
    public void updateOrder(Order order);
}