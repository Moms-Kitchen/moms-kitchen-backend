package com.proyect.momskitchen.backend.model;

import java.util.Date;
import java.util.List;

public class Order {

    float totalPrice;
    List<Meal> Orders;
    Date orderDate;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Meal> getOrders() {
        return Orders;
    }

    public void setOrders(List<Meal> orders) {
        Orders = orders;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
         
    
}