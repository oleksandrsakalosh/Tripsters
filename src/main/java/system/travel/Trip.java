package main.java.system.travel;

import main.java.system.user.Caravan;

import java.sql.Date;
import java.util.ArrayList;

public class Trip {
    private int id;
    private String name;
    private String imgScr;
    private int daysDur;
    private Date startDate;
    private Date endDate;
    private Caravan travelers;
    private ArrayList<City> road;

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

    public Caravan getTravelers() {
        return travelers;
    }

    public void setTravelers(Caravan travelers) {
        this.travelers = travelers;
    }

    public ArrayList<City> getRoad() {
        return road;
    }

    public void setRoad(ArrayList<City> road) {
        this.road = road;
    }

    public String getImgScr() {
        return imgScr;
    }

    public void setImgScr(String imgScr) {
        this.imgScr = imgScr;
    }

    public int getDaysDur() {
        return daysDur;
    }

    public void setDaysDur(int daysDur) {
        this.daysDur = daysDur;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addCity(City city){
        if(road == null)
            road = new ArrayList<City>(){{add(city);}};
        else
            road.add(city);
    }

    public float getPrice(){
        float price = 0;
        if(road != null) {
            for (City city : road)
                price += city.getBudget();
        }
        return price;
    }
}
