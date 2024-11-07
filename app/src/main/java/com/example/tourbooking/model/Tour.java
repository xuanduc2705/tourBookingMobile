package com.example.tourbooking.model;

import java.io.Serializable;

public class Tour implements Serializable {
    private int id,category_id;
    private String tour_name, description,address,image,time_tour,start_date,end_date,create_at,update_at,delete_at,guide_name,guide_phone,guide_image,location;
    private float price;

    public Tour() {
    }

    public Tour(int id, int category_id, String tour_name, String description, String address, String image, String time_tour, String start_date, String end_date, String create_at, String update_at, String delete_at, String guide_name, String guide_phone, String guide_image, String location, float price) {
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
        this.guide_name = guide_name;
        this.guide_phone = guide_phone;
        this.guide_image = guide_image;
        this.location = location;
        this.price = price;
    }
    public Tour(String address, int category_id, String create_at, String delete_at, String description, String end_date, String guide_image, String guide_name, String guide_phone, int id, String image, float price, String start_date, String time_tour, String tour_name, String update_at) {
        this.address = address;
        this.category_id = category_id;
        this.create_at = create_at;
        this.delete_at = delete_at;
        this.description = description;
        this.end_date = end_date;
        this.guide_image = guide_image;
        this.guide_name = guide_name;
        this.guide_phone = guide_phone;
        this.id = id;
        this.image = image;
        this.price = price;
        this.start_date = start_date;
        this.time_tour = time_tour;
        this.tour_name = tour_name;
        this.update_at = update_at;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGuide_image() {
        return guide_image;
    }

    public void setGuide_image(String guide_image) {
        this.guide_image = guide_image;
    }

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public String getGuide_phone() {
        return guide_phone;
    }

    public void setGuide_phone(String guide_phone) {
        this.guide_phone = guide_phone;
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


