package com.project.momskitchen.backend.persistence;

import com.project.momskitchen.backend.exceptions.MomsPersistenceException;

public interface Persistence {

    public void realizarConexion() throws MomsPersistenceException;

}