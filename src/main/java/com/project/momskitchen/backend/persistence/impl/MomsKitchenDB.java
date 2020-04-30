package com.project.momskitchen.backend.persistence.impl;

import java.math.BigDecimal;
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

        System.out.println("'holi'");
    }

    public void realizaConexion() throws MomsPersistenceException{

        try {            
            c = DriverManager.getConnection(urlDatabase,  usuarioDb, passwordDb);
            //System.out.println("La conexion se realizo sin problemas!");
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
            String sql = "INSERT INTO user (nombre,apellido,correo,contraseña,saldo,numerocedula) "
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

    public Boolean createMenu(Menu menu) throws MomsPersistenceException {
        Menu mn = menu;
        Boolean b = false;
        List<Meal> meals = mn.getMeals();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        String SQLcount = "SELECT count(distinct id) FROM public.\"menu\"";
        System.out.println(SQLcount);
        

        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            pstmt2 = c.prepareStatement(SQLcount,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet mnid = pstmt2.executeQuery();
            mnid.next();
            int idmn = mnid.getInt("count")+1;
            for (int i = 1; i < meals.size()+1; i++){

                String SQL = "INSERT INTO public.\"menu\" (id,\"NumberLine\",\"idMeal\",\"idUser\",prices,description) "
                +"VALUES ('"+idmn+"','"+i+"','"+meals.get(i-1).getId()+"','"+mn.getChef().getId()+"','"+meals.get(i-1).getPrice()+"','"+meals.get(i-1).getDescription()+"');";
                pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                b = pstmt.execute();
                b = true;

            }
            c.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
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
                mn.setDescription(rs.getString("description"));
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

    public List<Menu> getMenus() throws MomsPersistenceException{
        String SQL = "SELECT * FROM public.\"menu\" ";
        List<Menu> mns = new ArrayList<Menu>();
        int idMenuCreate;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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

    public List<Meal> getMeals() throws MomsPersistenceException{
        String SQL = "SELECT * FROM public.\"meal\" ";
        List<Meal> mns = new ArrayList<Meal>();
        int idMealCreate;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            int idMeal;
            rs.next();
            if(rs.absolute(1)){
                mns = new ArrayList<Meal>();
                idMeal = rs.getInt("id");
                mns.add(getMeal(idMeal));
                idMealCreate = idMeal;
                while(rs.next()){
                    idMeal = rs.getInt("id");
                    if(idMeal != idMealCreate){
                        mns.add(getMeal(idMeal));
                        idMealCreate = idMeal;
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

    public List<Meal> getMealsByName(String mealname) throws MomsPersistenceException {
        String SQL = "SELECT * FROM public.\"meal\" WHERE name ILIKE '%"+ mealname +"%'";

        Meal meal = null;
        List<Meal> meals = new ArrayList<Meal>();
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);            
            ResultSet rs = pstmt.executeQuery();  
            rs.next();          
            if(rs.absolute(1)){
                meal = new Meal(rs.getInt("id"),rs.getString("name"),rs.getBigDecimal("price"),rs.getString("description"));
                meals.add(meal);
                while(rs.next()){
                    meal = new Meal(rs.getInt("id"),rs.getString("name"),rs.getBigDecimal("price"),rs.getString("description"));
                    meals.add(meal);
                }                
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    public Boolean createMeal(Meal meal) throws MomsPersistenceException {
        Meal me = meal;
        Boolean b = false;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        String SQLcount = "SELECT count(distinct id) FROM public.\"meal\"";
        System.out.println(SQLcount);
        

        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            pstmt2 = c.prepareStatement(SQLcount,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet mnid = pstmt2.executeQuery();
            mnid.next();
            int idmn = mnid.getInt("count")+1;

            String SQL = "INSERT INTO public.\"meal\" (id,name,price,description) "
            +"VALUES ('"+idmn+"','"+me.getName()+"','"+me.getPrice()+"','"+me.getDescription()+"');";
            pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            b = pstmt.execute();
            b = true;

            
            c.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
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
            BigDecimal tp = null;
            rs.next();
            if(rs.absolute(1)){
                customer = getUser(rs.getInt("idCustomer"));
                chef = getUser(rs.getInt("idChef"));
                od = new Order(rs.getInt("id"),rs.getDate("date"),rs.getString("description"),customer,chef);
                tp  = new BigDecimal(rs.getInt("totalPrice"));
                idMenu = rs.getInt("idMenu");
                menus.add(getMenu(idMenu));
                while(rs.next()){
                    idMenu = rs.getInt("idMenu");
                    menus.add(getMenu(idMenu));
                }
                od.setMenus(menus);
                od.setTotalPrice(tp);
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return od;
    }

    public List<Order> getCustomerOrders(int idCustomer) throws MomsPersistenceException {
        String SQL = "SELECT * FROM public.\"order\" WHERE \"idCustomer\" = ? ";
        List<Order> ods = new ArrayList<Order>();
        int idOrderCreate;
        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            PreparedStatement pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idCustomer);
            ResultSet rs = pstmt.executeQuery();
            int idOrder;
            rs.next();
            if(rs.absolute(1)){
                ods = new ArrayList<Order>();
                idOrder = rs.getInt("id");
                ods.add(getOrder(idOrder));
                idOrderCreate = idOrder;
                while(rs.next()){
                    idOrder = rs.getInt("id");
                    if(idOrder != idOrderCreate){
                        ods.add(getOrder(idOrder));
                        idOrderCreate = idOrder;
                    }
                }
                c.close();
                pstmt.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ods;
        
    }

    public Boolean createOrder(Order order) throws MomsPersistenceException {
        Order or = order;
        Boolean b = false;
        List<Menu> menus = or.getMenus();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        String SQLcount = "SELECT count(distinct id) FROM public.\"order\"";
        System.out.println(SQLcount);
        

        try {
            if(c == null || c.isClosed()){
                realizaConexion();
            }
            pstmt2 = c.prepareStatement(SQLcount,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet mnid = pstmt2.executeQuery();
            mnid.next();
            int idmn = mnid.getInt("count")+1;
            for (int i = 1; i < menus.size()+1; i++){

                String SQL = "INSERT INTO public.\"menu\" (id,\"numLine\", description, date, \"idMenu\", \"idCustomer\", \"totalPrice\", \"idChef\") "
                +"VALUES ('"+idmn+"','"+i+"','"+or.getDescription()+"','"+or.getOrderDate()+"','"+menus.get(i-1).getId()+"','"+menus.get(i-1).getPrice()+"','"+or.getChef().getId()+"');";
                pstmt = c.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                b = pstmt.execute();
                b = true;

            }
            c.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
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