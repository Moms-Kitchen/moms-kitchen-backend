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
    int idCustomer;
    int idChef;

    public Order(int id, Date orderDate, String description, int idCustomer) {
        this.id = id;
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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getIdChef() {
        return idChef;
    }

    public void setIdChef(int idChef) {
        this.idChef = idChef;
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

    @Override
    public String toString() {
        return "Order [description=" + description + ", id=" + id + ", idCustomer=" + idCustomer + ", menus=" + menus
                + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + "]";
    }

      
}