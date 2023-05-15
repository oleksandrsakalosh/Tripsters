package main.java.system.travel;

import main.java.system.DBConnection;
import main.java.system.alert.Alerter;
import main.java.system.exception.CityNotFound;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class City {
    private int id;
    private String name;
    private String countryName;
    private float budget;
    private Date dayIn;
    private Date dayOut;

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

    public float getBudget() { return budget; }

    public void setBudget(float budget) { this.budget = budget; }

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

    public void newCityInTrip(String name, float budget, Date dayIn, Date dayOut, DBConnection DB) throws SQLException, CityNotFound {
        String query = "SELECT count(1) FROM city WHERE name = '" + name + "'";

        Statement st = DB.getNewStatement();
        ResultSet res = st.executeQuery(query);
        res.next();

        if(res.getInt(1) == 1){
            query = "SELECT * FROM city WHERE name = '" + name + "'";

            st = DB.getNewStatement();
            res = st.executeQuery(query);
            res.next();

            setId(res.getInt(1));
            setName(res.getString(2));
            setBudget(budget);
            setDayIn(dayIn);
            setDayOut(dayOut);
            int countryId = res.getInt(3);

            query = "SELECT name FROM country WHERE id = " + countryId;

            st = DB.getNewStatement();
            res = st.executeQuery(query);
            res.next();
            setCountryName(res.getString(1));
        }
        else
            throw new CityNotFound();

    }
}
