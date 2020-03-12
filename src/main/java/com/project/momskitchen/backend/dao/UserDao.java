package com.project.momskitchen.backend.dao;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.User;

public interface UserDao{
    
    public User consultUser(int id)throws MomsPersistenceException;
    
    void insertarUsuario(int cedula, String nombre, String apellido, String correo,String contra);

    void updateUsuario(User us);
    
}
