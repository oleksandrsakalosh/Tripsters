package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.system.TripstersApp;

import javax.swing.text.html.ImageView;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainController {
    TripstersApp sys;
    Stage primaryStage;


    MainController(TripstersApp sys, Stage primaryStage) {
        this.sys = sys;
        this.primaryStage = primaryStage;
    }

    public FXMLLoader switchScene(String fileName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        switch (fileName){
            case "../view/AppScene.fxml":
                loader.setControllerFactory(controllerClass -> new AppController(this.sys, this.primaryStage));
                break;
            case "../view/AuthorizationScene.fxml":
                loader.setControllerFactory(controllerClass -> new AuthorizationController(this.sys, this.primaryStage));
                break;
            case "../view/TripScene.fxml":
                loader.setControllerFactory(controllerClass -> new TripController(this.sys, this.primaryStage));
                break;
        }

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        return loader;
    }
}
