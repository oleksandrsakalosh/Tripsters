package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.travel.Trip;

public class TripController extends MainController{

    @FXML
    private Label name;

    public TripController(TripstersApp sys, Stage primaryStage, Scene preScene) {
        super(sys, primaryStage, preScene);
    }

    public void setData(Trip trip){
        name.setText(trip.getName());

    }
}
