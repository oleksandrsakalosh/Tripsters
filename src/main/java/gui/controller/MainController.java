package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.system.TripstersApp;

import java.io.IOException;

public class MainController {
    protected TripstersApp sys;
    protected Stage primaryStage;
    protected Scene preScene;

    MainController(TripstersApp sys, Stage primaryStage, Scene preScene) {
        this.sys = sys;
        this.primaryStage = primaryStage;
        this.preScene = preScene;
    }

    public void switchScene(String fileName, Scene preScene, ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        switch (fileName){
            case "../view/AppScene.fxml":
                loader.setControllerFactory(controllerClass -> new AppController(this.sys, this.primaryStage, preScene));
                break;
            case "../view/AuthorizationScene.fxml":
                loader.setControllerFactory(controllerClass -> new AuthorizationController(this.sys, this.primaryStage));
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
    }

    @FXML
    void goBack(ActionEvent event)  {
        primaryStage.setScene(preScene);
        primaryStage.show();
    }
}
