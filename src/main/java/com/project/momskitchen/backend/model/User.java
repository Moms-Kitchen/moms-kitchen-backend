package com.project.momskitchen.backend.model;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;

public class User implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    String address;
    String email;
    BigDecimal phone;
    boolean ischef;
    float rating;
    byte profilePicture;
    private String password;
    int role;

    //CreditCard card; /-- for future use
        

    public User(){
        this.id = 0;
        this.name = null;
        this.address = null;
        this.email = null;
        this.phone = new BigDecimal(0);
        this.ischef = false;
        this.rating = 0;
        this.profilePicture = 0x0;
        this.password = null;
    }

    public User(int id, String name,String address, String email, BigDecimal phone, Boolean chef, float rating, byte profilePicture, String password){
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.ischef = chef;
        this.rating = rating;
        this.profilePicture = profilePicture;
        this.password = password;
    }


    public User(int id, String name,String address, String email, BigDecimal phone, Boolean chef, float rating, byte profilePicture, String password, int role){
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.ischef = chef;
        this.rating = rating;
        this.profilePicture = profilePicture;
        this.password = password;
        this.role = role;
    }

    //User Constructor to deliver a login information package, used for login confirmation in the front end.
    public User(int id,String email, boolean ischef){
        this.id = id;
        this.name = null;
        this.address = null;
        this.email = email;
        this.phone = new BigDecimal(0);
        this.ischef = ischef;
        this.rating = 0;
        this.profilePicture = 0x0;
        this.password = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public byte getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contra) {
        this.password = contra;
    }    

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isIschef() {
        return ischef;
    }

    public void setIschef(boolean ischef) {
        this.ischef = ischef;
    }

    @Override
    public String toString() {
        return "User [address=" + address + ", email=" + email + ", id=" + id + ", ischef=" + ischef + ", name=" + name
                + ", password=" + password + ", phone=" + phone + ", profilePicture=" + profilePicture + ", rating="
                + rating + ", role=" + role + "]";
    }

    
}