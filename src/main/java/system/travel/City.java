package main.java.system.travel;

import main.java.system.services.Flight;
import main.java.system.services.Review;
import main.java.system.services.Room;

import java.sql.Date;
import java.util.ArrayList;

public class City {
    private int id;
    private String name;
    private String countryName;
    private String text;
    private ArrayList<Review> reviews;
    private Date dayIn;
    private Date dayOut;

    private Room room;
    private Flight flight;

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

    public String getCountryName() { return countryName; }

    public void setCountryName(String countryName) { this.countryName = countryName; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public ArrayList<Review> getReviews() { return reviews; }

    public void setReviews(ArrayList<Review> reviews) { this.reviews = reviews; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Date getDayIn() {
        return dayIn;
    }

    public void setDayIn(Date dayIn) {
        this.dayIn = dayIn;
    }

    public Date getDayOut() {
        return dayOut;
    }

    public void setDayOut(Date dayOut) {
        this.dayOut = dayOut;
    }
}
