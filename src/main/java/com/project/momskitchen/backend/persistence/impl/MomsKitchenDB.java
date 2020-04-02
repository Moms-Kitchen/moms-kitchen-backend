package com.project.momskitchen.backend.persistence.impl;

import java.sql.*;
import com.project.momskitchen.backend.exceptions.MomsPersistenceException;
import com.project.momskitchen.backend.model.Meal;
import com.project.momskitchen.backend.model.Menu;
import com.project.momskitchen.backend.model.Order;
import com.project.momskitchen.backend.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MomsKitchenDB {
    //Direccion del DB de prueba
    private String urlDatabase = "jdbc:postgresql://ec2-54-165-36-134.compute-1.amazonaws.com:5432/d3ckljhjikb54l";
    private String usuarioDb = "khmwcxngcevhfl";
    private String passwordDb= "00d145a9ac598e7f244c56b463bbe44d5a4bdc9820f5de415a04bd65745dfa02";
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
        String SQL = "SELECT * FROM public.\"user\" WHERE email = ? AND password = ?";

        User user = new User();
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.absolute(1)){
                user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getString("email"),rs.getBigDecimal("phone"), rs.getBoolean("chef"),rs.getFloat("rating"),rs.getByte("profilePicture"),rs.getString("password"));
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Menu getMenu(int idMenu) throws MomsPersistenceException{
        String SQL = "SELECT * FROM public.\"menu\" WHERE id = ? ";
        Menu mn = null;
        User chef = null;
        List<Meal> meals = new ArrayList<Meal>();
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idMenu);
            ResultSet rs = pstmt.executeQuery();
            int idMeal;
            rs.next();
            if(rs.absolute(1)){
                chef = getUser(rs.getInt("idUser"));
                mn = new Menu(rs.getInt("id"),chef,rs.getBigDecimal("prices"));
                idMeal = rs.getInt("idMeal");
                meals.add(getMeal(idMeal));
                while(rs.next()){
                    idMeal = rs.getInt("idMeal");
                    meals.add(getMeal(idMeal));
                }
                mn.setMeals(meals);
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mn;
    }

    public List<Menu> getUserMenu(int idUser) throws MomsPersistenceException{
        String SQL = "SELECT * FROM public.\"menu\" WHERE \"idUser\" = ? ";
        List<Menu> mns = new ArrayList<Menu>();
        int idMenuCreate;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            int idMenu;
            rs.next();
            if(rs.absolute(1)){
                mns = new ArrayList<Menu>();
                idMenu = rs.getInt("id");
                mns.add(getMenu(idMenu));
                idMenuCreate = idMenu;
                while(rs.next()){
                    idMenu = rs.getInt("id");
                    if(idMenu != idMenuCreate){
                        mns.add(getMenu(idMenu));
                        idMenuCreate = idMenu;
                    }
                }
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mns;
    }

    public Meal getMeal(int idMeal) throws MomsPersistenceException {
        String SQL = "SELECT * FROM public.\"meal\" WHERE id = ? ";

        Meal meal = null;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idMeal);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.absolute(1)){
                meal = new Meal(rs.getInt("id"),rs.getString("name"),rs.getBigDecimal("price"),rs.getString("description"));
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    public Order getOrder(int idOrder) throws MomsPersistenceException {
        String SQL = "SELECT * FROM public.\"order\" WHERE id = ? ";
        Order od = null;
        User customer = null;
        User chef = null;
        List<Menu> menus = new ArrayList<Menu>();
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idOrder);
            ResultSet rs = pstmt.executeQuery();
            int idMenu;
            rs.next();
            if(rs.absolute(1)){
                customer = getUser(rs.getInt("idCustomer"));
                chef = getUser(rs.getInt("idChef"));
                od = new Order(rs.getInt("id"),rs.getDate("date"),rs.getString("description"),customer,chef);
                idMenu = rs.getInt("idMenu");
                menus.add(getMenu(idMenu));
                while(rs.next()){
                    idMenu = rs.getInt("idMenu");
                    menus.add(getMenu(idMenu));
                }
                od.setMenus(menus);
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return od;
    }

    public User getUser(int idUser) throws MomsPersistenceException {
        String SQL = "SELECT * FROM public.\"user\" WHERE id = ? ";

        User user = null;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.absolute(1)){
                user = new User(rs.getInt("id"), rs.getString("name"),rs.getString("address"),rs.getString("email"),rs.getBigDecimal("phone"),rs.getBoolean("chef"),rs.getFloat("rating"),rs.getByte("profilePicture"),rs.getString("password"),rs.getInt("idrole"));
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User validateAuth(String email) throws MomsPersistenceException {
        String SQL = "SELECT * FROM public.\"user\" WHERE email = ? ";

        User user = null;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.absolute(1)){
                user = new User(rs.getInt("id"), rs.getString("name"),rs.getString("address"),rs.getString("email"),rs.getBigDecimal("phone"),rs.getBoolean("chef"),rs.getFloat("rating"),rs.getByte("profilePicture"),rs.getString("password"),rs.getInt("idrole"));
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