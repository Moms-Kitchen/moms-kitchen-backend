package com.project.momskitchen.backend.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {

    int id;
    BigDecimal totalPrice;
    List<Menu> menus;
    Date orderDate;
    String description;
    User Customer;
    User Chef;
    boolean pendingPayment;

    public Order(int id, Date orderDate, String description, User Customer, User Chef, boolean pendingPayment) {
        this.id = id;
        this.orderDate = orderDate;
        this.description = description;
        this.Customer = Customer;
        this.Chef = Chef;
        this.pendingPayment = pendingPayment;

    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrices) {
        this.totalPrice = totalPrices;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
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

    public User getCustomer() {
        return Customer;
    }

    public void setCustomer(User customer) {
        Customer = customer;
    }

    public User getChef() {
        return Chef;
    }

    public void setChef(User chef) {
        Chef = chef;
    }

    public boolean isPendingPayment() {
        return pendingPayment;
    }

    public void setPendingPayment(boolean pendingPayment) {
        this.pendingPayment = pendingPayment;
    }

    @Override
    public String toString() {
        return "Order [Chef=" + Chef + ", Customer=" + Customer + ", description=" + description + ", id=" + id
                + ", menus=" + menus + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ",pendingPayment=" + pendingPayment +"]";
    }

}