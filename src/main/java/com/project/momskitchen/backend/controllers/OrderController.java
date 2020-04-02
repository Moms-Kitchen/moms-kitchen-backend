package com.project.momskitchen.backend.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.momskitchen.backend.model.Order;
import com.project.momskitchen.backend.services.impl.OrderServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController{
    OrderServices os = new OrderServices();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET, path = "/{idOrder}")
    public ResponseEntity<?> getMenu(@PathVariable int idOrder){
                System.out.println("API");
        try {
            System.out.println("API TRY");
            Order or = os.getOrder(idOrder);
            System.out.println(or.toString());
            return new ResponseEntity<>(or, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET, path = "/customer/{idCustomer}")
    public ResponseEntity<?> getOrdersCustomer(@PathVariable int idCustomer){
        try {
            List<Order> ods = os.getCustomerOrders(idCustomer);
            return new ResponseEntity<>(ods, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }
}