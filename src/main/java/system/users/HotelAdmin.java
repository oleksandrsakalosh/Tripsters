package main.java.system.users;


import main.java.system.travel.Trip;

import java.awt.*;
import java.util.ArrayList;

public abstract class HotelAdmin implements Person{
    private int id;
    private String name;
    private String password;
    private Image profilePicture;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public abstract ArrayList<Integer> getCaravanId();

    public abstract void setCaravanId(ArrayList<Integer> caravanId);

    public abstract ArrayList<Trip> getMyTrips();

    public abstract void setMyTrips(ArrayList<Trip> myTrips);
}
