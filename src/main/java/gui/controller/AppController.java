package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.travel.Trip;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController extends MainController implements Initializable {
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
    private Label tripCount;

    @FXML
    private Button refreshMyTripsButton;

    @FXML
    private GridPane myJourneysGrid;

    public AppController(TripstersApp sys, Stage primaryStage, Scene preScene) {
        super(sys, primaryStage, preScene);
    }

    @FXML
    public void switchToFirstTab(MouseEvent mouseEvent) {
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
        if(!tabPane.getSelectionModel().getSelectedItem().getText().equals("Saved")){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshMyTripsButton.getOnAction().handle(new ActionEvent());
    }

    @FXML
    void refreshMyTrips(ActionEvent event) throws SQLException, IOException {
        myJourneysGrid.getChildren().clear();
        sys.setUserTrips();
        List<Trip> trips = sys.getLoggedUser().getMyTrips();
        tripCount.setText(Integer.toString(trips.size()) + " found");
        int column = 0;
        int row = 1;
        try {
            for(Trip trip : trips){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/TripBox.fxml"));
                Pane pane = fxmlLoader.load();
                TripBoxController tripBoxController = fxmlLoader.getController();
                tripBoxController.setData(trip, sys, primaryStage, preScene);

                if(column == 3){
                    column = 0;
                    ++row;
                }

                myJourneysGrid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
