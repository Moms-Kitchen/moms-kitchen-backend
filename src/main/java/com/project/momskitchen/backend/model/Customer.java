package com.project.momskitchen.backend.model;

import java.util.List;

public class Customer extends User {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    List<Order> orders;

    public Customer(){
        super();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> lorders) {
        orders = lorders;
    }
}