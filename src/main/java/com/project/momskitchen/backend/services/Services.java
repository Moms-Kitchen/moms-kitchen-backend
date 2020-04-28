package com.project.momskitchen.backend.services;

import java.util.ArrayList;

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