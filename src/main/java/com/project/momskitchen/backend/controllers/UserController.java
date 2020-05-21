package com.project.momskitchen.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.User;
import com.project.momskitchen.backend.services.impl.UserServices;

@RequestMapping("/session")
@RestController
public class UserController {

    UserServices usi = new UserServices();

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET, path = "/{email}/{password}")
    public ResponseEntity<?> verifyLogin(@PathVariable("email") String email, @PathVariable("password") String password)
            throws InterruptedException {
        User user = usi.authenticateUser(email, password);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.POST, path = "/user")
    public ResponseEntity<?> addUser(@RequestBody User user) throws InterruptedException, MomsPersistenceException
    {
        boolean res = usi.addUser(user);
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "https://momskitchenapp.herokuapp.com")
    @RequestMapping(method = RequestMethod.GET, path = "/user/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email){
                System.out.println("API");
        try {
            System.out.println("API TRY");
            User user = usi.validateAuth(email);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
    }
}