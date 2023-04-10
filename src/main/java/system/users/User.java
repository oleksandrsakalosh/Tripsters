package main.java.system.users;

import main.java.system.travel.Trip;

import java.awt.*;
import java.util.ArrayList;

public class User implements Person{
    private int id;
    private String name;
    private String login;
    private String password;
    private Image profilePicture;
    private ArrayList<Integer> caravanId;
    private ArrayList<Trip> myTrips;

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

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getProfilePicture() { return profilePicture; }

    public void setProfilePicture(Image profilePicture) { this.profilePicture = profilePicture; }

    public ArrayList<Integer> getCaravanId() {
        return caravanId;
    }

    public void setCaravanId(ArrayList<Integer> caravanId) {
        this.caravanId = caravanId;
    }

    public ArrayList<Trip> getMyTrips() {
        return myTrips;
    }

    public void setMyTrips(ArrayList<Trip> myTrips) {
        this.myTrips = myTrips;
    }
}
