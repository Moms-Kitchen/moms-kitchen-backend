package com.project.momskitchen.backend.controllers;

import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.services.impl.MenuServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET, path = "/{idMenu}")
    public ResponseEntity<?> getMenu(@PathVariable int idMenu){
        try {
            Menu mn = ms.getMenu(idMenu);
            return new ResponseEntity<>(mn, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
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

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET , path = "/list")
    public ResponseEntity<?> getMenus(){
        try {
            List<Menu> mns = ms.getMenus();
            return new ResponseEntity<>(mns, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.POST , path = "/createMenu")
    public ResponseEntity<?> setMenu(@RequestBody Menu menu){
        try {
            
            System.out.println("post Menu");
            System.out.println("\n\n\nMENU: " + menu + "\n\n\n");
            Boolean b = ms.setMenu(menu);
            return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 406", HttpStatus.NOT_ACCEPTABLE);
        }
    }

}

