package com.project.momskitchen.backend.model;

import java.util.List;

public class Customer extends User {
    List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> lorders) {
        orders = lorders;
    }
}