package com.project.momskitchen.backend.persistence.impl;

import java.sql.*;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.User;

public class MomsKitchenDB {
    //Direccion del DB de prueba
    static String urlDatabase = "";
    static String usuarioDb = "";
    static String passwordDb= "";
    static Connection c = null;

    public void realizaConexion() throws MomsPersistenceException{

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(urlDatabase,  usuarioDb, passwordDb);
            System.out.println("La conexion se realizo sin problemas!");
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }        
    }

    public void insertarUsuario(User user){
        //Recupera conexion y crea Statement para el db
        User usr = user;
        Statement stmt = null;
        
        //Valida si existe una conexion abierta al db y si no trata de abrir una
        if(c == null){

            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
            } catch (Exception e) {
            }
        }

        try{
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
            String sql = "INSERT INTO usuario (nombre,apellido,correo,contraseña,saldo,numerocedula) "
               +"VALUES ('"+usr.getName()+"','"+usr.getEmail()+"','"+usr.getContra()+","+usr.getId()+");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            //c.close();
        }catch(Exception e){
        }
    }
}