package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.system.travel.Trip;

public class TripController {

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label duration;

    @FXML
    private Label price;

    public void setData(Trip trip){
        Image image;
        if(trip.getImgScr() != null) {
            image = new Image(getClass().getResourceAsStream("../img/" + trip.getImgScr()));
            img.setImage(image);
        }
        name.setText(trip.getName());
        duration.setText(Integer.toString(trip.getDaysDur()) + " Days");
        price.setText(Float.toString(trip.getPrice()) + "€");
    }
}
