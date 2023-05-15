package main.java.system.user;

import main.java.system.DBConnection;
import main.java.system.exception.UserNotFound;
import main.java.system.travel.Trip;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User{
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

    public void newUserInCaravan(String login, DBConnection DB) throws SQLException, UserNotFound {
        String query = "SELECT count(1) FROM tripster WHERE login = '" + login + "'";

        Statement st = DB.getNewStatement();
        ResultSet res = st.executeQuery(query);
        res.next();

        if(res.getInt(1) == 1){
            query = "SELECT * FROM tripster WHERE login = '" + login + "'";

            st = DB.getNewStatement();
            res = st.executeQuery(query);
            res.next();

            setId(res.getInt(1));
            setName(res.getString(2));
            setLogin(login);
        }
        else
            throw new UserNotFound();
    }
}
