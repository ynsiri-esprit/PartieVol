package org.example.travelagency;

import Services.Impl.AgentServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePageController {
    @FXML
    private Label travelCountLabel;

    @FXML
    private Label flightCountLabel;

    @FXML
    private Label totalSumLabel;

    @FXML
    private Label generalTravelCountLabel;

    @FXML
    private Label generalFlightCountLabel;

    @FXML
    private Label generalTotalSumLabel;

    @FXML
    private Label Name;

    @FXML
    private MenuItem TripList;


    public void initialize() {
        loadData();
    }

    private void loadData() {

        AgentServiceImpl agentService = new AgentServiceImpl();
        try {
            travelCountLabel.setText(String.valueOf(agentService.StatsBookingToDay()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            flightCountLabel.setText(String.valueOf(agentService.StatsFlightToDay()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            totalSumLabel.setText(String.valueOf(agentService.StatsSumToDay())+"TND");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            generalTravelCountLabel.setText(String.valueOf(agentService.StatsBookingGeneral()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            generalFlightCountLabel.setText(String.valueOf(agentService.StatsFlightGneral()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            generalTotalSumLabel.setText(String.valueOf(agentService.StatsSumGneral())+"TND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserName(String userName) {
        Name.setText(userName);
    }

    public void GoListTrip() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BookingList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 744, 400);
        Stage stage = new Stage();
        stage.setTitle("Liste des voyages");
        stage.setScene(scene);
        stage.show();
    }

    public void AddTrip() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddTripForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 400);
        Stage stage = new Stage();
        stage.setTitle("Ajout d'un voyage");
        stage.setScene(scene);
        stage.show();
    }

    public void FlightList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ListFlight.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 400);
        Stage stage = new Stage();
        stage.setTitle("Liste des vols");
        stage.setScene(scene);
        stage.show();
    }

    public void AddFlight() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddFlight.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 400);
        Stage stage = new Stage();
        stage.setTitle("Ajouter un vol");
        stage.setScene(scene);
        stage.show();
    }

}
