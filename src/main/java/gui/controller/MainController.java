package main.java.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    public Label tabName1;
    @FXML
    public Label tabName2;
    @FXML
    public Label tabName3;
    @FXML
    public Label tabName4;
    @FXML
    public Label tabName5;

    @FXML
    private TabPane tabPane;

    @FXML
    public void switchToFirstTab(MouseEvent mouseEvent) {
        System.out.println(1);
        if(!tabPane.getSelectionModel().getSelectedItem().getText().equals("My Journeys")) {
            tabPane.getSelectionModel().select(0);
            List<Label> labels = new ArrayList<Label>(){{
                add(tabName1);
                add(tabName2);
                add(tabName3);
                add(tabName4);
                add(tabName5);
            }};
            for(Label label : labels)
                label.setTextFill(Color.web("#676767"));
            tabName1.setTextFill(Color.web("#ffffff"));
        }
    }

    @FXML
    public void switchToSecondTab(MouseEvent mouseEvent) {
        if(!tabPane.getSelectionModel().getSelectedItem().getText().equals("Explore")){
            tabPane.getSelectionModel().select(1);
            List<Label> labels = new ArrayList<Label>(){{
                add(tabName1);
                add(tabName2);
                add(tabName3);
                add(tabName4);
                add(tabName5);
            }};
            for(Label label : labels)
                label.setTextFill(Color.web("#676767"));
            tabName2.setTextFill(Color.web("#ffffff"));
        }
    }

    public void switchToThirdTab(MouseEvent mouseEvent) {
        if(!tabPane.getSelectionModel().getSelectedItem().getText().equals("Start New")){
            tabPane.getSelectionModel().select(2);
            List<Label> labels = new ArrayList<Label>(){{
                add(tabName1);
                add(tabName2);
                add(tabName3);
                add(tabName4);
                add(tabName5);
            }};
            for(Label label : labels)
                label.setTextFill(Color.web("#676767"));
            tabName3.setTextFill(Color.web("#ffffff"));
        }
    }

    public void switchToFourthTab(MouseEvent mouseEvent) {
        if(!tabPane.getSelectionModel().getSelectedItem().getText().equals("Caravans")){
            tabPane.getSelectionModel().select(3);
            List<Label> labels = new ArrayList<Label>(){{
                add(tabName1);
                add(tabName2);
                add(tabName3);
                add(tabName4);
                add(tabName5);
            }};
            for(Label label : labels)
                label.setTextFill(Color.web("#676767"));
            tabName4.setTextFill(Color.web("#ffffff"));
        }
    }

    public void switchToFifthTab(MouseEvent mouseEvent) {
        if(!tabPane.getSelectionModel().getSelectedItem().getText().equals("Profile")){
            tabPane.getSelectionModel().select(4);
            List<Label> labels = new ArrayList<Label>(){{
                add(tabName1);
                add(tabName2);
                add(tabName3);
                add(tabName4);
                add(tabName5);
            }};
            for(Label label : labels)
                label.setTextFill(Color.web("#676767"));
            tabName5.setTextFill(Color.web("#ffffff"));
        }
    }
}
