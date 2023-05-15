package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.travel.City;
import main.java.system.travel.Trip;
import main.java.system.user.Caravan;
import main.java.system.user.User;

import java.io.IOException;
import java.util.ArrayList;

public class TripController extends MainController{

    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label duration;
    @FXML
    private ImageView tripImg;
    @FXML
    private GridPane tripstersGrid;
    @FXML
    private GridPane citiesGrid;

    private Trip trip;

    public TripController(TripstersApp sys, Stage primaryStage) {
        super(sys, primaryStage);
    }

    public void setData(Trip trip){
        this.trip = trip;
        name.setText(trip.getName());
        price.setText(trip.getPrice() + "€");
        duration.setText(trip.getDaysDur() + " Days");
        Image image;
        if(trip.getImgScr() != null) {
            image = new Image(getClass().getResourceAsStream("../img/" + trip.getImgScr()));
            tripImg.setImage(image);
        }
        setTripstersGrid();
        setCitiesGrid();
    }

    public void setTripstersGrid(){
        Caravan caravan = trip.getTravelers();

        int column = 0;
        int row = 1;
        for(User user : caravan.getMembers()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/UserBox.fxml"));
            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserBoxController userBoxController = fxmlLoader.getController();
            userBoxController.setUserName(user);

            tripstersGrid.add(pane, column, row++);
        }
    }

    public void setCitiesGrid(){
        ArrayList<City> road = trip.getRoad();

        int column = 0;
        int row = 1;
        if(road == null || road.isEmpty())
            return;
        for(City city : road){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/CityBox.fxml"));
            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CityBoxController cityBoxController = fxmlLoader.getController();
            cityBoxController.setData(city);

            citiesGrid.add(pane, column, row++);
        }
    }

    @FXML
    public void goBack(MouseEvent mouseEvent) {
        switchScene("../view/AppScene.fxml");
    }
}
