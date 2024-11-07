package com.example.tourbooking.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String image, name, create_at, update_at, delete_at;

    public Category() {
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(int id, String image, String name, String create_at, String update_at, String delete_at) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.create_at = create_at;
        this.update_at = update_at;
        this.delete_at = delete_at;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(String delete_at) {
        this.delete_at = delete_at;
    }
}


