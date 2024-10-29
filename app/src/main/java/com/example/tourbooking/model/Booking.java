package com.example.tourbooking.model;

import java.io.Serializable;

public class Booking implements Serializable {
    private int id,tour_id,user_id,numOfPeople,status;
    private float total;
    private  String bookingDate;

    public Booking() {
    }

    public Booking( int id, int numOfPeople, int status, float total, int tour_id, int user_id,String bookingDate) {
        this.bookingDate = bookingDate;
        this.id = id;
        this.numOfPeople = numOfPeople;
        this.status = status;
        this.total = total;
        this.tour_id = tour_id;
        this.user_id = user_id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
