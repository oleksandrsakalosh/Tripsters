package main.java.system.services;

import java.util.ArrayList;

public class Airline {
    private int id;
    private String name;
    private ArrayList<Review> reviews;

    public int getCompanyId() {
        return id;
    }

    public void setCompanyId(int companyId) {
        this.id = companyId;
    }

    public String getCompanyName() {
        return name;
    }

    public void setCompanyName(String companyName) {
        this.name = companyName;
    }

    public ArrayList<Review> getCompanyReviews() {
        return reviews;
    }

    public void setCompanyReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
