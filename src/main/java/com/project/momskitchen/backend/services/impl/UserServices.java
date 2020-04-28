package com.project.momskitchen.backend.services.impl;

import java.util.ArrayList;

import com.project.momskitchen.backend.dao.impl.UserDAOImpl;
import com.project.momskitchen.backend.model.User;
import com.project.momskitchen.backend.services.Services;

import org.springframework.stereotype.Service;

@Service
public class UserServices implements Services {

    UserDAOImpl udi = new UserDAOImpl();

    public User authenticateUser(String email, String password){         
        return udi.readUser(email, password);
    }

    public User validateAuth(String email){         
        return udi.readUser(email);
    }

    @Override
    public void createElement(Object elemento) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object Read() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object Update() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void Delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public ArrayList<Object> allElements() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getElmentObject(Object obj) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
 

    //@Autowired
    //private 

    
}