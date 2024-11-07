package com.example.tourbooking.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id,status;
    private String name,address,phone,email,dob,image,password;
    private boolean isAdmin;


    public User() {
    }

    public User(String address, String dob, String email, int id, String image, boolean isAdmin, String name, String phone,String password, int status) {
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.id = id;
        this.image = image;
        this.isAdmin = isAdmin;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.status = status;
    }

    public User(String address, String dob, String email, String image, boolean isAdmin, String name, String password, String phone, int status) {
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.image = image;
        this.isAdmin = isAdmin;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
