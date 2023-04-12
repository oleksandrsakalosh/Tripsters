package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    protected Scene preScene;

    private Trip trip;

    @FXML
    void openTrip(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TripScene.fxml"));
        loader.setControllerFactory(controllerClass -> new TripController(this.sys, this.primaryStage, preScene));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TripController controller = loader.getController();
        controller.setData(trip);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void setData(Trip trip, TripstersApp sys, Stage primaryStage, Scene preScene){
        Image image;
        if(trip.getImgScr() != null) {
            image = new Image(getClass().getResourceAsStream("../img/" + trip.getImgScr()));
            img.setImage(image);
        }
        name.setText(trip.getName());
        duration.setText(Integer.toString(trip.getDaysDur()) + " Days");
        price.setText(Float.toString(trip.getPrice()) + "€");
        this.trip = trip;
        this.sys = sys;
        this.primaryStage = primaryStage;
        this.preScene = preScene;
    }
}
