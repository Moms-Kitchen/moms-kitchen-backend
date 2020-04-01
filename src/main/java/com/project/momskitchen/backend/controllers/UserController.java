package com.project.momskitchen.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.momskitchen.backend.model.User;
import com.project.momskitchen.backend.services.impl.UserServices;

@RestController
@RequestMapping(value = "/session")
public class UserController {

    UserServices usi = new UserServices();

    @RequestMapping(method = RequestMethod.GET, path = "{email}/{password}")
    public ResponseEntity<?> verifyLogin(@PathVariable("email") String email,
            @PathVariable("password") String password) {
                System.out.println("API");
        try {
            System.out.println("API TRY");
            User user = usi.authenticateUser(email, password);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            System.out.println("API CATCH");
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("ERROR 404", HttpStatus.NOT_FOUND);
        }
        
    }

}