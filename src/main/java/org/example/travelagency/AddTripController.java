package org.example.travelagency;

import Entities.VoyageOrganise;
import Services.Impl.VoyageOrganiseImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTripController {

    @FXML private DatePicker dateDepartPicker;
    @FXML
    private CheckComboBox<String> itineraryComboBox;
    @FXML
    private CheckComboBox<String> pointsOfInterestComboBox;
    @FXML private CheckBox guideCheckBox;
    @FXML private TextField tariffField;
    @FXML private TextArea descriptionField;

    private VoyageOrganise voyage;

    @FXML
    private void onSave() throws SQLException {
        voyage= new VoyageOrganise();

        List<String> selectedItineraries = new ArrayList<>();
        if (itineraryComboBox.getCheckModel().getCheckedItems().size() > 0) {
            for (String itinerary : itineraryComboBox.getCheckModel().getCheckedItems()) {
                selectedItineraries.add(itinerary);
            }
            voyage.setItineraires(selectedItineraries);
        }

        List<String> selectedPointsOfInterest = new ArrayList<>();
        if (pointsOfInterestComboBox.getCheckModel().getCheckedItems().size() > 0) {
            for (String pointOfInterest : pointsOfInterestComboBox.getCheckModel().getCheckedItems()) {
                selectedPointsOfInterest.add(pointOfInterest);
                voyage.setPointsIneret(selectedPointsOfInterest);

            }}
            voyage.setDescription(descriptionField.getText());
            voyage.setGuideDisponible(guideCheckBox.isSelected());
            if (tariffField != null && tariffField.getText() != "") {
                voyage.setTarif(Float.parseFloat(tariffField.getText()));

            }

            if (dateDepartPicker.getValue() != null) {
                voyage.setDateDepart(java.sql.Date.valueOf(dateDepartPicker.getValue()));
            }

            VoyageOrganiseImpl v = new VoyageOrganiseImpl();
            v.ajouter(voyage);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Les informations du voyage ont été enregistrées avec succès !");
            alert.showAndWait();
        }


    @FXML
    private void onCancel() {
        Stage stage = (Stage) dateDepartPicker.getScene().getWindow();
        stage.close();
    }


}
