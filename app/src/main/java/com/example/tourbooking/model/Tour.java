package com.example.tourbooking.model;

import java.io.Serializable;

public class Tour implements Serializable {
    private int id,category_id;
    private String tour_name, description,address,image,time_tour,start_date,end_date,create_at,update_at,delete_at;
    private float price;

    public Tour() {
    }

    public Tour(int id, int category_id, String tour_name, String description, String address, String image, String time_tour, String start_date, String end_date, String create_at, String update_at, String delete_at, float price) {
        this.id = id;
        this.category_id = category_id;
        this.tour_name = tour_name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.time_tour = time_tour;
        this.start_date = start_date;
        this.end_date = end_date;
        this.create_at = create_at;
        this.update_at = update_at;
        this.delete_at = delete_at;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTour_name() {
        return tour_name;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime_tour() {
        return time_tour;
    }

    public void setTime_tour(String time_tour) {
        this.time_tour = time_tour;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}


