package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.travel.City;
import main.java.system.travel.Trip;

public class CityBoxController {
    @FXML
    private Label cityName;
    @FXML
    private Label countryName;
    @FXML
    private Label dayIn;
    @FXML
    private Label dayOut;
    @FXML
    private Label budget;

    public void setData(City city){
        cityName.setText(city.getName());
        countryName.setText(city.getCountryName());
        dayIn.setText(city.getDayIn().toString());
        dayOut.setText(city.getDayOut().toString());
        budget.setText(city.getBudget() + "€");
    }
}
