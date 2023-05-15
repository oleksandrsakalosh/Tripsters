package main.java.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.system.TripstersApp;
import main.java.system.alert.Alerter;
import main.java.system.travel.City;
import main.java.system.travel.Trip;
import main.java.system.user.Caravan;
import main.java.system.user.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @FXML
    private ImageView tripImg;
    @FXML
    private TextField tripName;
    @FXML
    private TextField newTravelerLogin;
    @FXML
    private GridPane tripstersGrid;
    @FXML
    private GridPane citiesGrid;
    @FXML
    private TextField newCity;
    @FXML
    private TextField newBudget;
    @FXML
    private TextField newDayIn;
    @FXML
    private TextField newDayOut;

    ArrayList<City> newRoad;

    public AppController(TripstersApp sys, Stage primaryStage) {
        super(sys, primaryStage);
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

    @FXML
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

    @FXML
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

    @FXML
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

        sys.setNewTrip(new Trip());
        sys.getNewTrip().setRoad(new ArrayList<City>());
        sys.getNewTrip().setTravelers(new Caravan());
        try {
            sys.addUserToTrip(sys.getLoggedUser().getLogin());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        refreshTripstersGrid();
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
                tripBoxController.setData(trip, sys, primaryStage);

                pane.setOnMouseClicked(e -> {
                    FXMLLoader loader = switchScene("../view/TripScene.fxml");
                    TripController tripController = loader.getController();
                    tripController.setData(tripBoxController.getTrip());
                });

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

    @FXML
    void addImg(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file != null){
            Image image = new Image(file.toURI().toString());
            tripImg.setImage(image);
            sys.getNewTrip().setImgScr(file.getAbsolutePath());
        }
    }

    @FXML
    void addNewCity(ActionEvent event) throws SQLException {
        if(newCity.getText().equals("") || newBudget.getText().equals("")
                || newDayIn.getText().equals("") || newDayOut.getText().equals("")){
            Alerter alert = new Alerter("Error", "Please fill in all fields");
            alert.showAlert();
        }
        else{
            if(isValidDate(newDayIn.getText()) && isValidDate(newDayOut.getText())) {
                if (sys.addCityToTrip(newCity.getText(), Float.parseFloat(newBudget.getText()),
                        Date.valueOf(newDayIn.getText()), Date.valueOf(newDayOut.getText()))) {
                    refreshCitiesGrid();
                    newCity.clear();
                    newBudget.clear();
                    newDayIn.clear();
                    newDayOut.clear();
                }
            }
            else{
                Alerter alert = new Alerter("Error", "Enter valid date");
                alert.showAlert();
            }
        }
    }

    @FXML
    void addNewTripster(ActionEvent event) throws SQLException {
        if(newTravelerLogin.getText().equals("")){
            Alerter alert = new Alerter("Error", "Please fill in user login");
            alert.showAlert();
        }
        else{
            if(sys.addUserToTrip(newTravelerLogin.getText())){
                refreshTripstersGrid();
                newTravelerLogin.clear();
            }
        }
    }

    @FXML
    void saveNewTrip(ActionEvent event) throws SQLException, IOException {
        if(tripName.getText().equals("")){
            Alerter alert = new Alerter("Error", "Enter name of trip");
            alert.showAlert();
            return;
        }
        if(sys.getNewTrip().getRoad().isEmpty()){
            Alerter alert = new Alerter("Error", "Road is empty");
            alert.showAlert();
            return;
        }

        sys.getNewTrip().setName(tripName.getText());
        sys.saveNewTrip();

        sys.setNewTrip(new Trip());
        sys.getNewTrip().setRoad(new ArrayList<City>());
        sys.getNewTrip().setTravelers(new Caravan());
        try {
            sys.addUserToTrip(sys.getLoggedUser().getLogin());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tripName.clear();
        Image image = new Image(getClass().getResourceAsStream("../img/no-image.jpg"));
        tripImg.setImage(image);
        refreshTripstersGrid();
        refreshCitiesGrid();
        refreshMyTrips(event);
    }

    public boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void refreshCitiesGrid(){
        citiesGrid.getChildren().clear();
        ArrayList<City> road = sys.getNewTrip().getRoad();

        int column = 0;
        int row = 1;
        if(road == null || road.isEmpty())
            return;
        for(City city : road){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/CityBox.fxml"));
            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CityBoxController cityBoxController = fxmlLoader.getController();
            cityBoxController.setData(city);

            citiesGrid.add(pane, column, row++);
        }
    }

    public void refreshTripstersGrid(){
        tripstersGrid.getChildren().clear();
        Caravan caravan = sys.getNewTrip().getTravelers();

        int column = 0;
        int row = 1;
        for(User user : caravan.getMembers()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/UserBox.fxml"));
            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserBoxController userBoxController = fxmlLoader.getController();
            userBoxController.setUserName(user);

            tripstersGrid.add(pane, column, row++);
        }
    }
}
