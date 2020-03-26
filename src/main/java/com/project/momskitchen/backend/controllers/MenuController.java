package com.project.momskitchen.backend.controllers;

import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.services.impl.MenuServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/session")
public class MenuController{
    
    MenuServices ms = new MenuServices();

    @RequestMapping(method = RequestMethod.GET, path = "/{idMenu}")
    public ResponseEntity<?> getMenu(@PathVariable int idMenu){
                System.out.println("API");
        try {
            System.out.println("API TRY");
            Menu mn = ms.getMenu(idMenu);
            return new ResponseEntity<>(mn, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
        
    }

}

