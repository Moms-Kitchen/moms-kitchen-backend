package com.project.momskitchen.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.project.momskitchen.backend.dao.impl.OrderDAOimpl;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Order;
import com.project.momskitchen.backend.services.Services;

import org.springframework.stereotype.Service;

@Service
public class OrderServices implements Services {

    OrderDAOimpl od = new OrderDAOimpl();

    public OrderServices() {
    }

    public Order getOrder(int idOrder) throws MomsPersistenceException{
        Order order = od.consultOrder(idOrder);
        return order;
    }

    public List<Order> getCustomerOrders(int idCustomer) throws MomsPersistenceException{
        List<Order> orders = od.consultCustomerOrders(idCustomer);
        return orders;
    }

    public Boolean setOrder(Order order) throws MomsPersistenceException {
        Boolean b = false;
        b = od.createOrder(order);
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