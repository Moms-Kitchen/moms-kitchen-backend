package com.project.momskitchen.backend.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {

    BigDecimal totalPrice;
    List<Meal> orders;
    Date orderDate;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrices) {
        this.totalPrice = totalPrices;
    }

    public List<Meal> getOrders() {
        return orders;
    }

    public void setOrders(List<Meal> lorders) {
        orders = lorders;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
         
    
}