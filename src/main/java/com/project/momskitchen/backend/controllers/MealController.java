package com.project.momskitchen.backend.controllers;

import com.project.momskitchen.backend.model.Meal;
import com.project.momskitchen.backend.services.impl.MealServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestMapping("/meal")
@RestController
public class MealController {
    MealServices ms = new MealServices();

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET, path = "/{idMeal}")
    public ResponseEntity<?> getMeal(@PathVariable int idMeal){
        try {
            Meal ml = ms.getMeal(idMeal);
            return new ResponseEntity<>(ml, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET, path = "/stringmeal/{mealname}")
    public ResponseEntity<?> getMealsbymatch(@PathVariable String mealname){
        try {
            List<Meal> ml = ms.getMealsByName(mealname);
            return new ResponseEntity<>(ml, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET , path = "/list")
    public ResponseEntity<?> getMeals(){
        try {
            List<Meal> mls = ms.getMeals();
            return new ResponseEntity<>(mls, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.POST , path = "/createMenu")
    public ResponseEntity<?> setMeal(@RequestBody Meal meal){
        try {
            System.out.println("post Menu");
            Boolean b = ms.setMeal(meal);
            return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 406", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}