package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.system.user.User;

public class UserBoxController {
    @FXML
    private Label userName;

    public void setUserName(User user){
        userName.setText(user.getName());
    }
}
