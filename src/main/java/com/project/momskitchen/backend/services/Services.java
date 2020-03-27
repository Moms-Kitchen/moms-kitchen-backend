package com.project.momskitchen.backend.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

public interface Services {


    //CRUD
    //CREATE
    public void createElement(Object elemento);
    
    //READ
    public Object Read();

    //UPDATE
    public Object Update();

    //DELETE
    public void Delete();

    public ArrayList<Object> allElements();
    public Object getElmentObject(Object obj) throws Exception;
}