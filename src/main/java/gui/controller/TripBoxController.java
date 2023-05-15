package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.travel.Trip;

import java.io.IOException;

public class TripBoxController{

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label duration;

    @FXML
    private Label price;

    protected TripstersApp sys;
    protected Stage primaryStage;

    private Trip trip;

    public Trip getTrip() {
        return trip;
    }

    public void setData(Trip trip, TripstersApp sys, Stage primaryStage){
        Image image;
        if(trip.getImgScr() != null) {
            image = new Image(getClass().getResourceAsStream("../img/" + trip.getImgScr()));
            img.setImage(image);
        }
        name.setText(trip.getName());
        duration.setText(trip.getDaysDur() + " Days");
        price.setText(trip.getPrice() + "€");
        this.trip = trip;
        this.sys = sys;
        this.primaryStage = primaryStage;
    }
}
