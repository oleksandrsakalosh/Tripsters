package main.java.system.services;

import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private ArrayList<Review> reviews;

    public int getHotelId() {
        return id;
    }

    public void setHotelId(int hotelId) {
        this.id = hotelId;
    }

    public String getHotelName() {
        return name;
    }

    public void setHotelName(String name) {
        this.name = name;
    }

    public ArrayList<Review> getHotelReviews() {
        return reviews;
    }

    public void setHotelReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
