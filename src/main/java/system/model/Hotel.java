package main.java.system.model;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private List<Review> reviews;

    public int getHotelId() {
        return id;
    }

    public void setHotelId(int hotelId) { this.id = hotelId; }

    public String getHotelName() {
        return name;
    }

    public void setHotelName(String name) {
        this.name = name;
    }

    public List<Review> getHotelReviews() {
        return reviews;
    }

    public void setHotelReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
