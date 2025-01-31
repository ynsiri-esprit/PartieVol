package org.example.travelagency;

import Entities.Vol;
import Services.Impl.VolServicesImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Services.Impl.VoyageOrganiseImpl;

import java.time.LocalTime;
import java.util.Date;

public class AddFlightController {

    @FXML
    private DatePicker dateArriveePicker;
    @FXML
    private TextField compagnieField;
    @FXML
    private TextField aeroportDepartField;
    @FXML
    private TextField aeroportArriveeField;
    @FXML
    private TextField heureDepartField;
    @FXML
    private TextField heureArriveeField;
    @FXML
    private TextField tariffField;
    @FXML
    private TextArea descriptionField;

    private Vol vol;

    @FXML
    private void initialize() {
        vol = new Vol();
    }

    @FXML
    private void onSave() {
        if (compagnieField.getText().isEmpty() || aeroportDepartField.getText().isEmpty() || aeroportArriveeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champs manquants", "Veuillez remplir tous les champs obligatoires.");
            return;
        }

        Date dateArrivee = java.sql.Date.valueOf(dateArriveePicker.getValue());
        String compagnie = compagnieField.getText();
        String aeroportDepart = aeroportDepartField.getText();
        String aeroportArrivee = aeroportArriveeField.getText();
        String heureDepart = heureDepartField.getText();
        String heureArrivee = heureArriveeField.getText();
        float tarif = 0.0f;
        try {
            tarif = Float.parseFloat(tariffField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Format incorrect", "Le tarif doit être un nombre valide.");
            return;
        }
        String description = descriptionField.getText();
        vol.setDateArrivee(dateArrivee);
        vol.setCompagnie(compagnie);
        vol.setAeroportDepart(aeroportDepart);
        vol.setAeroportArrivee(aeroportArrivee);
        vol.setHeureDepart(new Date(heureDepart));
        vol.setHeureArrivee(new Date(heureArrivee));
        vol.setTarif(tarif);
        vol.setDescription(description);
        VolServicesImpl volServices = new VolServicesImpl();
        try {
            volServices.ajouter(vol);  // Sauvegarde du vol
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Le vol a été enregistré avec succès.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'enregistrement.");
        }
    }

    @FXML
    private void onCancel() {
        compagnieField.clear();
        aeroportDepartField.clear();
        aeroportArriveeField.clear();
        heureDepartField.clear();
        heureArriveeField.clear();
        tariffField.clear();
        descriptionField.clear();
        dateArriveePicker.setValue(null);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
