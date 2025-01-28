package org.example.travelagency;

import Services.Impl.AgentServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    // Méthode d'initialisation pour charger les données (sans passer le userName ici)
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

    // Méthode pour définir le nom de l'utilisateur dans le Label
    public void setUserName(String userName) {
        Name.setText(userName);  // Afficher le nom d'utilisateur dans le Label
    }
}
