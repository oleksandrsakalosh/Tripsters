package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.system.TripstersApp;

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
        sys.logUser(login.getText(), password.getText());
        switchScene("../view/AppScene.fxml", event);
    }

    @FXML
    void registerButton(ActionEvent event) throws SQLException {
        sys.addNewUser(name.getText(), newLogin.getText(), newPassword.getText());
    }


}
