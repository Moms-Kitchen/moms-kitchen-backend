package com.project.momskitchen.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.momskitchen.backend.model.User;
import com.project.momskitchen.backend.services.impl.UserServices;


@RequestMapping("/session")
@RestController
public class UserController {

    UserServices usi = new UserServices();

    @CrossOrigin(origins = "http://localhost:3000")    
    @RequestMapping(method = RequestMethod.GET, path = "/{email}/{password}")
    public ResponseEntity<?> verifyLogin(@PathVariable("email") String email,
           @PathVariable("password") String password) throws InterruptedException
    {
        User user = usi.authenticateUser(email, password);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    } 

}