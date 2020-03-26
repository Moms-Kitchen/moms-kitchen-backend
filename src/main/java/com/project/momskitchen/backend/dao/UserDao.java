package com.project.momskitchen.backend.dao;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.User;

public interface UserDao{
    
    public User readUser(int id)throws MomsPersistenceException;

    public User readUser(String email, String password);
    
    void insertarUsuario(int cedula, String nombre, String apellido, String correo,String contra);

    void updateUsuario(User us);
    
}
