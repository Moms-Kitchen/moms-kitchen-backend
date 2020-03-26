package com.project.momskitchen.backend.dao.impl;

import com.project.momskitchen.backend.dao.UserDao;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.User;
import com.project.momskitchen.backend.persistence.impl.MomsKitchenDB;

public class UserDAOImpl implements UserDao {

    private MomsKitchenDB bg = new MomsKitchenDB();

    @Override
    public User readUser(int id) throws MomsPersistenceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User readUser(String email, String password) {
        
        try {
            return bg.authentication(email, password);
        } catch (MomsPersistenceException e) {            
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insertarUsuario(int cedula, String nombre, String apellido, String correo, String contra) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUsuario(User us) {
        // TODO Auto-generated method stub

    }



}