package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.alert.Alerter;
import main.java.system.exception.LogInException;
import main.java.system.exception.SignInException;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorizationController extends MainController {
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField name;

    @FXML
    private TextField newLogin;

    @FXML
    private PasswordField newPassword;

    public AuthorizationController(TripstersApp sys, Stage primaryStage) {
        super(sys, primaryStage);
    }

    @FXML
    void loginButton(ActionEvent event) throws IOException, SQLException {
        try {
            if(sys.logUser(login.getText(), password.getText()))
                switchScene("../view/AppScene.fxml");
        } catch (LogInException e) {
            Alerter alert = new Alerter("Error", e.getText());
            alert.showAlert();
        }
    }

    @FXML
    void registerButton(ActionEvent event) throws SQLException {
        if(name.getText().equals("") || newLogin.getText().equals("") || newPassword.getText().equals("")){
            Alerter alert = new Alerter("Alert", "You haven't filled in all fields.");
            alert.showAlert();
        }
        else {
            try {
                if(sys.addNewUser(name.getText(), newLogin.getText(), newPassword.getText())){
                    name.clear();
                    newLogin.clear();
                    newPassword.clear();
                }
            } catch (SignInException e) {
                Alerter alert = new Alerter("Error", e.getText());
                alert.showAlert();
            }
        }
    }


}
