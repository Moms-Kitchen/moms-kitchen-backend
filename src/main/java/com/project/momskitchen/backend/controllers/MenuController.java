package com.project.momskitchen.backend.controllers;

import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.services.impl.MenuServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/menus")
public class MenuController{
    
    MenuServices ms = new MenuServices();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET, path = "/{idMenu}")
    public ResponseEntity<?> getMenu(@PathVariable int idMenu){
                System.out.println("API");
        try {
            System.out.println("API TRY");
            Menu mn = ms.getMenu(idMenu);
            System.out.println(mn.toString());
            return new ResponseEntity<>(mn, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET, path = "/chef/{idCocinero}")
    public ResponseEntity<?> getMenusCocinero(@PathVariable int idCocinero){
        try {
            List<Menu> mns = ms.getChefMenus(idCocinero);
            return new ResponseEntity<>(mns, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

}

