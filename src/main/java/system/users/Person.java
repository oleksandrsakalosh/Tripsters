package main.java.system.users;


import main.java.system.travel.Trip;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface Person {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);
    
    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    Image getProfilePicture();

    void setProfilePicture(Image profilePicture);

    List<Integer> getCaravanId();

    void setCaravanId(ArrayList<Integer> caravans);

    ArrayList<Trip> getMyTrips();

    void setMyTrips(ArrayList<Trip> myTrips);
}
