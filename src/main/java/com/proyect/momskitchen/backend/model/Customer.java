package com.proyect.momskitchen.backend.model;

import java.util.List;

public class Customer extends User {
    List<Order> Orders;

    public List<Order> getOrders() {
        return Orders;
    }

    public void setOrders(List<Order> orders) {
        Orders = orders;
    }
}