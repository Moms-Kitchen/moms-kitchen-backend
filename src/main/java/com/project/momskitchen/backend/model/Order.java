package com.project.momskitchen.backend.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {

    int id;
    BigDecimal totalPrice;
    List<Menu> orders;
    Date orderDate;
    String description;
    int idCustomer;

    public Order(int id, List<Menu> orders, Date orderDate, String description, int idCustomer) {
        this.id = id;
        this.orders = orders;
        this.orderDate = orderDate;
        this.description = description;
        this.idCustomer = idCustomer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrices) {
        this.totalPrice = totalPrices;
    }

    public List<Menu> getOrders() {
        return orders;
    }

    public void setOrders(List<Menu> lorders) {
        orders = lorders;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    
         
    
}