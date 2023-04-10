package main.java.system;

import main.java.system.travel.City;
import main.java.system.travel.Trip;
import main.java.system.users.Person;
import main.java.system.users.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TripstersApp {
    private Person loggedUser;
    private DBConnection DB;

    public TripstersApp() throws SQLException {
        DB = new DBConnection();
    }

    public Person getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Person loggedUser) {
        this.loggedUser = loggedUser;
    }

    public DBConnection getDB() {
        return DB;
    }

    public void addNewUser(String userName, String userLogin, String userPassword) throws SQLException {
        // query
        String query = "INSERT INTO tripster(login, password, name) VALUES(?, ?, ?)";

        PreparedStatement pst = DB.getConnection().prepareStatement(query);

        pst.setString(1, userLogin);
        pst.setString(2, userPassword);
        pst.setString(3, userName);
        pst.executeUpdate();
    }

    public void logUser(String login, String password) throws SQLException, IOException {
        String query = "SELECT count(1) FROM tripster WHERE login = '" + login + "' AND password = '" + password + "' ";

        Statement st = DB.getConnection().createStatement();
        ResultSet res = st.executeQuery(query);
        res.next();

        if(res.getInt(1) == 1){
            Person user = new User();

            query = "SELECT *, LENGTH(picture) FROM tripster WHERE login = '" + login + "'";
            res = st.executeQuery(query);
            res.next();

            user.setId(res.getInt(1));
            user.setName(res.getString(2));
            user.setLogin(res.getString(3));
            user.setPassword(res.getString(4));
            int len = res.getInt(6);

            Image img = null;
            if(len != 0) {
                byte[] buf = res.getBytes(5);

                File file = new File("src/main/java/gui/img/imgprofile-picture.jpg");

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(buf, 0, len);
                img = ImageIO.read(file);
            }
            user.setProfilePicture(img);

            query = "SELECT id FROM caravan WHERE " + user.getId() + " = ANY(members)";
            res = st.executeQuery(query);

            ArrayList<Integer> caravans = new ArrayList<>();
            while (res.next()){
                caravans.add(res.getInt(1));
            }
            user.setCaravanId(caravans);

            setLoggedUser(user);
        }
    }

    public void setUserTrips() throws SQLException, IOException {
        ArrayList<Trip> tripList = new ArrayList<>();

        List<Integer> userCaravans = loggedUser.getCaravanId();

        for(int caravanId : userCaravans){
            String query = "SELECT *, LENGTH(image) FROM trip WHERE caravan_id = " + caravanId;
            Statement st = DB.getConnection().createStatement();
            ResultSet res = st.executeQuery(query);

            while(res.next()) {
                Trip trip = new Trip();
                trip.setId(res.getInt(1));
                trip.setName(res.getString(2));

                String filename = null;
                int len = res.getInt(5);
                if(len != 0) {
                    filename = "trip-" + trip.getId() + ".jpg";
                    String path = "src/main/java/gui/img/" + filename;
                    byte[] buf = res.getBytes(4);

                    File file = new File(path);

                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(buf, 0, len);
                }
                trip.setImgScr(filename);
                tripList.add(trip);
            }

            for(Trip trip : tripList){
                query = "SELECT city.name, country.name, start_date, end_date FROM cities_in_trip \n" +
                        "JOIN trip ON cities_in_trip.trip_id = trip.id\n" +
                        "JOIN city ON cities_in_trip.city_id = city.id\n" +
                        "JOIN country ON city.country_id = country.id\n" +
                        "WHERE trip.id = " + trip.getId() + "\n" +
                        "ORDER BY start_date ASC";
                st = DB.getConnection().createStatement();
                res = st.executeQuery(query);
                Date tripStart = null;
                Date tripEnd = null;

                while(res.next()){
                    City city = new City();
                    city.setName(res.getString(1));
                    city.setCountryName(res.getString(2));
                    city.setDayIn(res.getDate(3));
                    city.setDayOut(res.getDate(4));

                    if(tripStart == null)
                        tripStart = city.getDayIn();
                    tripEnd = city.getDayOut();
                    trip.addCity(city);
                }
                if (tripEnd != null) {
                    trip.setDaysDur((int) TimeUnit.DAYS.convert(tripEnd.getTime() - tripStart.getTime(), TimeUnit.MILLISECONDS));
                }
                trip.setPrice(800);
            }

        }
        loggedUser.setMyTrips(tripList);
    }
}
