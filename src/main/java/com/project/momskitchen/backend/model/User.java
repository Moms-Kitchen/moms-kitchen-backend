package com.project.momskitchen.backend.model;

import java.lang.reflect.Constructor;

public class User{

    int id;
    String name;
    String address;
    String email;
    float phone;
    boolean chef;
    float rating;
    String profilePicture;
    private String password;
    int role;

    //CreditCard card; /-- for future use
    
    public User(){
        
    }

    public User(String name,String address, String email, float phone, Boolean chef, float rating, String profilePicture, String password){
        this.id = 0;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.chef = chef;
        this.rating = rating;
        this.profilePicture = profilePicture;
        this.password = password;
    }

    public User(String name,String address, String email, float phone, Boolean chef, float rating, String profilePicture, String password, int role){
        this.id = 0;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.chef = chef;
        this.rating = rating;
        this.profilePicture = profilePicture;
        this.password = password;
        this.role = role;
    }

    //User Constructor to deliver worthless information package, used for login confirmation in the front end.
    public User(String email){
        this.id = 0;
        this.name = null;
        this.address = null;
        this.email = email;
        this.phone = 0;
        this.chef = false;
        this.rating = 0;
        this.profilePicture = null;
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

    public float getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contra) {
        this.password = contra;
    }

    public boolean isChef() {
        return chef;
    }

    public void setChef(boolean chef) {
        this.chef = chef;
    }

    public void setPhone(float phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}