package main.java.system;

import main.java.system.alert.Alerter;
import main.java.system.exception.CityNotFound;
import main.java.system.exception.LogInException;
import main.java.system.exception.SignInException;
import main.java.system.exception.UserNotFound;
import main.java.system.travel.City;
import main.java.system.travel.Trip;
import main.java.system.user.Caravan;
import main.java.system.user.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TripstersApp {
    private User loggedUser;
    private DBConnection DB;

    private Trip newTrip;

    public TripstersApp() throws SQLException {
        DB = new DBConnection();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public DBConnection getDB() {
        return DB;
    }

    public Trip getNewTrip() { return newTrip; }

    public void setNewTrip(Trip newTrip) { this.newTrip = newTrip; }

    public boolean addNewUser(String userName, String userLogin, String userPassword) throws SQLException, SignInException {
        String query = "SELECT count(1) FROM tripster WHERE login = '" + userLogin + "'";

        Statement st = DB.getNewStatement();
        ResultSet res = st.executeQuery(query);
        res.next();

        //check if login isn't taken
        if(res.getInt(1) != 1) {
            // query
            query = "INSERT INTO tripster(login, password, name) VALUES(?, ?, ?)";

            PreparedStatement pst = DB.getConnection().prepareStatement(query);

            pst.setString(1, userLogin);
            pst.setString(2, userPassword);
            pst.setString(3, userName);
            pst.executeUpdate();

            return true;
        }
        else
            throw new SignInException();
    }

    public boolean logUser(String login, String password) throws SQLException, IOException, LogInException {
        String query = "SELECT count(1) FROM tripster WHERE login = '" + login + "' AND password = '" + password + "' ";

        Statement st = DB.getNewStatement();
        ResultSet res = st.executeQuery(query);
        res.next();

        //check if login and password are correct
        if(res.getInt(1) == 1){
            User user = new User();

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
            return true;
        }
        else
            throw new LogInException();
    }

    public void setUserTrips() throws SQLException, IOException {
        ArrayList<Trip> userTrips = new ArrayList<>();

        List<Integer> userCaravans = loggedUser.getCaravanId();

        for(int caravanId : userCaravans){
            ArrayList<Trip> tripList = new ArrayList<>();

            String query = "SELECT *, LENGTH(image) FROM trip WHERE caravan_id = " + caravanId;
            Statement st = DB.getNewStatement();
            ResultSet res = st.executeQuery(query);

            while(res.next()) {
                Trip trip = new Trip();
                trip.setId(res.getInt(1));
                trip.setName(res.getString(2));

                String filename = null;
                int len = res.getInt(5);
                if(len != 0) {
                    filename = "trip-" + trip.getId() + ".jpg";
                    String path = "out/production/Tripsters/main/java/gui/img/" + filename;
                    byte[] buf = res.getBytes(4);

                    File file = new File(path);

                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(buf, 0, len);
                    fos.close();
                }
                trip.setImgScr(filename);
                tripList.add(trip);
            }

            query = "SELECT * FROM caravan \n" +
                    "WHERE id = " + caravanId;
            st = DB.getNewStatement();
            res = st.executeQuery(query);
            res.next();

            Caravan caravan = new Caravan();
            caravan.setId(res.getInt(1));
            ArrayList<User> members = new ArrayList<>();
            Integer[] memberId = (Integer[]) res.getArray(2).getArray();
            for(int id : memberId){
                query = "SELECT id, name FROM tripster \n" +
                        "WHERE id = " + id;
                st = DB.getNewStatement();
                res = st.executeQuery(query);
                res.next();

                User user = new User();
                user.setId(res.getInt(1));
                user.setName(res.getString(2));

                members.add(user);
            }
            caravan.setMembers(members);


            for(Trip trip : tripList){
                trip.setTravelers(caravan);
                query = "SELECT city.name, country.name, start_date, end_date, budget FROM cities_in_trip \n" +
                        "JOIN trip ON cities_in_trip.trip_id = trip.id\n" +
                        "JOIN city ON cities_in_trip.city_id = city.id\n" +
                        "JOIN country ON city.country_id = country.id\n" +
                        "WHERE trip.id = " + trip.getId() + "\n" +
                        "ORDER BY start_date ASC";
                st = DB.getNewStatement();
                res = st.executeQuery(query);
                Date tripStart = null;
                Date tripEnd = null;

                while(res.next()){
                    City city = new City();
                    city.setName(res.getString(1));
                    city.setCountryName(res.getString(2));
                    city.setDayIn(res.getDate(3));
                    city.setDayOut(res.getDate(4));
                    city.setBudget(res.getFloat(5));

                    if(tripStart == null)
                        tripStart = city.getDayIn();
                    tripEnd = city.getDayOut();
                    trip.addCity(city);
                }
                if (tripStart != null) {
                    trip.setDaysDur((int) TimeUnit.DAYS.convert(tripEnd.getTime() - tripStart.getTime(), TimeUnit.MILLISECONDS));
                    trip.setStartDate(tripStart);
                    trip.setEndDate(tripEnd);
                }
                else{
                    trip.setDaysDur(0);
                }
                userTrips.add(trip);
            }
        }
        loggedUser.setMyTrips(userTrips);
    }

    public boolean addCityToTrip(String name, float budget, Date dayIn, Date dayOut) throws SQLException {
        City city = new City();
        try {
            city.newCityInTrip(name, budget, dayIn, dayOut, DB);

            newTrip.addCity(city);

            return true;
        } catch (CityNotFound e) {
            Alerter alert = new Alerter("Error", e.getText());
            alert.showAlert();

            return false;
        }
    }

    public boolean addUserToTrip(String login) throws SQLException {
        User user = new User();
        try {
            user.newUserInCaravan(login, DB);

            newTrip.getTravelers().addUser(user);

            return true;
        } catch (UserNotFound e) {
            Alerter alert = new Alerter("Error", e.getText());
            alert.showAlert();

            return false;
        }
    }

    public void saveNewTrip() throws SQLException {
        //inserting caravan
        String query = "INSERT INTO caravan(members) VALUES(?)";
        PreparedStatement pst = DB.getConnection().prepareStatement(query);
        Integer[] members = new Integer[newTrip.getTravelers().getMembers().size()];
        int i = 0;
        for(User user : newTrip.getTravelers().getMembers()){
            members[i] = user.getId();
            i++;
        }
        Array array = DB.getConnection().createArrayOf("INTEGER", members);
        pst.setArray(1, array);

        pst.executeUpdate();

        query = "SELECT MAX(id) FROM caravan";
        Statement st = DB.getNewStatement();
        ResultSet res = st.executeQuery(query);
        res.next();
        int caravanId = res.getInt(1);

        loggedUser.getCaravanId().add(caravanId);

        //inserting trip
        if(newTrip.getImgScr() != null){
            query = "INSERT INTO trip(name, caravan_id, image) VALUES(?, ?, ?)";
            pst = DB.getConnection().prepareStatement(query);

            File img = new File(newTrip.getImgScr());
            try {
                FileInputStream fin = new FileInputStream(img);
                pst.setBinaryStream(3, fin, (int) img.length());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            query = "INSERT INTO trip(name, caravan_id) VALUES(?, ?)";
            pst = DB.getConnection().prepareStatement(query);
        }


        pst.setString(1, newTrip.getName());
        pst.setInt(2, caravanId);

        pst.executeUpdate();

        query = "SELECT MAX(id) FROM trip";
        st = DB.getNewStatement();
        res = st.executeQuery(query);
        res.next();
        int tripId = res.getInt(1);

        query = "INSERT INTO cities_in_trip(trip_id, city_id, start_date, end_date, budget) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement finalPst = DB.getConnection().prepareStatement(query);
        newTrip.getRoad().forEach((e) -> {
            try {
                finalPst.setInt(1, tripId);
                finalPst.setInt(2, e.getId());
                finalPst.setDate(3, e.getDayIn());
                finalPst.setDate(4, e.getDayOut());
                finalPst.setFloat(5, e.getBudget());
                finalPst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
