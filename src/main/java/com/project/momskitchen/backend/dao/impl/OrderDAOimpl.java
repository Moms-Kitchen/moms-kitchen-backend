package com.project.momskitchen.backend.dao.impl;

import com.project.momskitchen.backend.dao.OrderDao;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.model.Order;
import com.project.momskitchen.backend.persistence.impl.MomsKitchenDB;


public class OrderDAOimpl implements OrderDao {

    private MomsKitchenDB bg = new MomsKitchenDB();

	@Override
	public Order consultOrder() throws MomsPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order consultOrder(int id) throws MomsPersistenceException {
		try {
            return bg.getOrder(id);
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public void insertarMenu(Menu menus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

}