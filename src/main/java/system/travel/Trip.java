package main.java.system.travel;

import main.java.system.users.Caravan;

import java.util.ArrayList;

public class Trip {
    private int id;
    private String name;
    private String imgScr;
    private float price;
    private int daysDur;
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


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public void addCity(City city){
        if(road == null)
            road = new ArrayList<City>(){{add(city);}};
        else
            road.add(city);
    }
}
