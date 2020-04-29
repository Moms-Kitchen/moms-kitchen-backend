package com.project.momskitchen.backend.controllers;

import javax.annotation.PostConstruct;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;
import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.model.Order;
import com.project.momskitchen.backend.services.impl.MealServices;
import com.project.momskitchen.backend.services.impl.MenuServices;
import com.project.momskitchen.backend.services.impl.OrderServices;
import com.project.momskitchen.backend.services.impl.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @Autowired
    private MenuServices menuservices; 

    @Autowired
    private OrderServices orderservices;

    @Autowired
    private MealServices mealservices;

    @Autowired
    private UserServices userservices;

    @Autowired
    private SimpMessagingTemplate mgt;
    private static SimpMessagingTemplate mgt2;

    @PostConstruct
    private void initMgt2 () {
        mgt2 = this.mgt;
    }

    @MessageMapping("/createMenu.{Menu}")
    public  void createMenu(@DestinationVariable Menu menu ) throws MomsPersistenceException{
        menuservices.setMenu(menu);
        mgt.convertAndSend("/topic", menuservices.getMenus());
    }

    @MessageMapping("/createOrder.{Order}")
    public  void createOrder(@DestinationVariable Order order ) throws MomsPersistenceException{
        orderservices.setOrder(order);
        mgt.convertAndSend("/topic", orderservices.getOrders());
    }

    @MessageMapping("/createMeal.{Meal}")
    public  void createMeal(@DestinationVariable Meal meal ) throws MomsPersistenceException{
        mealservices.setMeal(meal);
        mgt.convertAndSend("/topic", mealservices.getMeals());
    }

}