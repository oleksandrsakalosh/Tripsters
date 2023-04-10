package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.system.TripstersApp;

import java.io.IOException;

public class MainController {
    protected TripstersApp sys;
    protected Stage primaryStage;

    public MainController(TripstersApp sys, Stage primaryStage) {
        this.sys = sys;
        this.primaryStage = primaryStage;
    }

    public void setSys(TripstersApp sys, Stage primaryStage) {
        this.sys = sys;
        this.primaryStage = primaryStage;
    }

    public void switchScene(String fileName, ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setControllerFactory(controllerClass -> new AppController(this.sys, this.primaryStage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainController controller = loader.getController();
        controller.setSys(this.sys, this.primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
