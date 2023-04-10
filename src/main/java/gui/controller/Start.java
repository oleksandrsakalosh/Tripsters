package main.java.gui.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.system.TripstersApp;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TripstersApp sys = new TripstersApp();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AuthorizationScene.fxml"));
        loader.setControllerFactory(controllerClass -> new AuthorizationController(sys, primaryStage));
        Parent root = loader.load();

        primaryStage.setTitle("Tripsters");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
