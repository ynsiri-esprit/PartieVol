package org.example.travelagency;

import Entities.VoyageOrganise;
import Services.Impl.VoyageOrganiseImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateTripController {

    @FXML
    private DatePicker dateDepartPicker;
    @FXML
    private CheckComboBox<String> itineraryComboBox;
    @FXML
    private CheckComboBox<String> pointsOfInterestComboBox;
    @FXML private CheckBox guideCheckBox;
    @FXML private TextField tariffField;
    @FXML private TextArea descriptionField;

    private VoyageOrganise voyage;

    public void setVoyage(VoyageOrganise voyage)  {
        this.voyage = voyage;
        Date dateDepart = voyage.getDateDepart();
        LocalDate localDate = LocalDate.parse(dateDepart.toString());
        dateDepartPicker.setValue(localDate);
        if(voyage.getItineraires().size()>0)
        {
            for (String itineraire : voyage.getItineraires()) {
                itineraryComboBox.getCheckModel().check(itineraire);

            }   }     if(voyage.getPointsIneret().size()>0)
            for (String point : voyage.getPointsIneret()) {
                pointsOfInterestComboBox.getCheckModel().check(point);

            }        guideCheckBox.setSelected(voyage.isGuideDisponible());
        tariffField.setText(String.valueOf(voyage.getTarif()));
        descriptionField.setText(voyage.getDescription());
    }

    @FXML
    private void onSave() throws SQLException {
        int test=0;
        System.out.println(itineraryComboBox.getCheckModel().getCheckedItems());
        System.out.println(voyage.getItineraires());

        List<String> selectedItineraries = new ArrayList<>();
        if (itineraryComboBox.getCheckModel().getCheckedItems().size() > 0) {
            for (String itinerary : itineraryComboBox.getCheckModel().getCheckedItems()) {
                selectedItineraries.add(itinerary);
            }
            if (selectedItineraries.equals(voyage.getItineraires())) {
                test++;
            }
        else {
            voyage.setItineraires(selectedItineraries);
        }}
        List<String> selectedPointsOfInterest = new ArrayList<>();
        if (pointsOfInterestComboBox.getCheckModel().getCheckedItems().size() > 0) {
            for (String pointOfInterest : pointsOfInterestComboBox.getCheckModel().getCheckedItems()) {
                selectedPointsOfInterest.add(pointOfInterest);
            }
            if (selectedPointsOfInterest.equals(voyage.getPointsIneret())) {
                test++;
            } else {
                voyage.setPointsIneret(selectedPointsOfInterest);
            }
        }
        if(descriptionField.getText()!=null && !descriptionField.getText().isEmpty())
            if (descriptionField.getText().equals(voyage.getDescription())) {
                test++;
            }
        else{
            voyage.setDescription(descriptionField.getText());
            }
        if(voyage.isGuideDisponible()==guideCheckBox.isSelected()) {
            test++;
        }
        else {
            voyage.setGuideDisponible(guideCheckBox.isSelected());
        }
        if(tariffField.getText()!=null && !tariffField.getText().isEmpty())
        if(Float.parseFloat(tariffField.getText())==voyage.getTarif())
            test++;
        else {
            voyage.setTarif(Float.parseFloat(tariffField.getText()));
        }
        if (dateDepartPicker.getValue().toString().equals(voyage.getDateDepart().toString())) {
            test++;
        } if (dateDepartPicker.getValue() != null){
            voyage.setDateDepart(java.sql.Date.valueOf(dateDepartPicker.getValue()));

        }
        else
            System.out.println(test);
        if(test==6)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("aucune informaion a ete modifié!");
            alert.showAndWait();
        }
        else
        {
            VoyageOrganiseImpl v=new VoyageOrganiseImpl();
            v.update(voyage);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Les informations du voyage ont été enregistrées avec succès !");
            alert.showAndWait();
        }}


    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) dateDepartPicker.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Action échouée");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
