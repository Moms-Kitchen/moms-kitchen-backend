package com.project.momskitchen.backend.controllers;

import javax.annotation.PostConstruct;

import com.project.momskitchen.backend.services.impl.MealServices;
import com.project.momskitchen.backend.services.impl.MenuServices;
import com.project.momskitchen.backend.services.impl.OrderServices;
import com.project.momskitchen.backend.services.impl.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
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
}