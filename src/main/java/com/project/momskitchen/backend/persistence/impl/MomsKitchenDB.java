package com.project.momskitchen.backend.persistence.impl;

import java.sql.*;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MomsKitchenDB {
    //Direccion del DB de prueba
    private String urlDatabase = "jdbc:postgresql://ec2-54-152-175-141.compute-1.amazonaws.com:5432/daokt720d027tv";
    private String usuarioDb = "xjatwzrxvchluw";
    private String passwordDb= "a3c944efbd9ecb37a83ee555464b1b49b04803ce8ce85f97dbcafa7f632d28c7";
    static Connection c = null;

    public static void main(String[] args) throws MomsPersistenceException {
        MomsKitchenDB bg = new MomsKitchenDB();
        bg.realizaConexion();
    }

    public void realizaConexion() throws MomsPersistenceException{

        try {            
            c = DriverManager.getConnection(urlDatabase,  usuarioDb, passwordDb);
            System.out.println("La conexion se realizo sin problemas!");
        } catch (SQLException ex) {
            Logger.getLogger(MomsKitchenDB.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (Exception ex) {
                Logger.getLogger(MomsKitchenDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try{
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
            String sql = "INSERT INTO usuario (nombre,apellido,correo,contraseña,saldo,numerocedula) "
               +"VALUES ('"+usr.getName()+"','"+usr.getEmail()+"','"+usr.getPassword()+","+usr.getId()+");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            //c.close();
        }catch(Exception ex){
            Logger.getLogger(MomsKitchenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User authentication(String email, String password) throws MomsPersistenceException {
        String SQL = "SELECT email FROM public.\"user\" WHERE email = ? AND password = ?";

        User user = null;
        try {
            realizaConexion();
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.absolute(1)){
                user = new User(rs.getString("email"));
                System.out.println("User: " + user.toString());
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}