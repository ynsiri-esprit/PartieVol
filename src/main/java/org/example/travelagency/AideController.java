package org.example.travelagency;

import Entities.Aide;
import Services.Impl.AideServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class AideController {

    private final AideServiceImpl aideService = new AideServiceImpl();
    private Aide selectedAide = null;

    @FXML
    private TableView<Aide> tableAide;
    @FXML
    private TableColumn<Aide, Integer> colId;
    @FXML
    private TableColumn<Aide, String> colQuestion;
    @FXML
    private TableColumn<Aide, String> colReponse;

    @FXML
    private TextField txtQuestion;
    @FXML
    private TextField txtReponse;

    private ObservableList<Aide> aideList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Liaison des colonnes avec les propriétés de Aide
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colQuestion.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        colReponse.setCellValueFactory(cellData -> cellData.getValue().reponseProperty());

        // Chargement des données dans la table
        chargerAides();

        // Ajout d'un listener pour mettre à jour les champs de texte lorsque la sélection change
        tableAide.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtQuestion.setText(newValue.getQuestion());
                txtReponse.setText(newValue.getReponse());
            }
        });
    }



    private void chargerAides() {
        try {
            List<Aide> aides = aideService.getAll();
            aideList.setAll(aides);  // Remplir la liste observable
            tableAide.setItems(aideList); // Mettre à jour la TableView
        } catch (SQLException e) {
            afficherAlerte("Erreur", "Impossible de charger les aides", e.getMessage());
        }
    }


    @FXML
    void ajouterAide(ActionEvent event) {
        String question = txtQuestion.getText().trim();
        String reponse = txtReponse.getText().trim();

        if (question.isEmpty() || reponse.isEmpty()) {
            afficherAlerte("Champ vide", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            Aide aide = new Aide(0, question, reponse);
            aideService.ajouter(aide);
            chargerAides();
            txtQuestion.clear();
            txtReponse.clear();
        } catch (SQLException e) {
            afficherAlerte("Erreur", "Ajout échoué", e.getMessage());
        }
    }

    @FXML
    private void modifierAide() {
        Aide selectedAide = tableAide.getSelectionModel().getSelectedItem();
        if (selectedAide != null) {
            try {
                selectedAide.setQuestion(txtQuestion.getText());
                selectedAide.setReponse(txtReponse.getText());
                aideService.update(selectedAide);
                chargerAides();
                txtQuestion.clear();
                txtReponse.clear();
                afficherAlerte( "Succès", "Modification réussie.");
            } catch (SQLException e) {
                afficherAlerte("Erreur", "Échec de la modification.");
            }
        } else {
            afficherAlerte( "Attention", "Veuillez sélectionner une ligne à modifier.");
        }
    }



    @FXML
    void supprimerAide(ActionEvent event) {
        Aide selectedAide = tableAide.getSelectionModel().getSelectedItem();
        if (selectedAide == null) {
            afficherAlerte("Sélection requise", "Veuillez sélectionner une aide à supprimer.");
            return;
        }

        try {
            aideService.supprimer(selectedAide);
            chargerAides();
            txtQuestion.clear();
            txtReponse.clear();
        } catch (SQLException e) {
            afficherAlerte("Erreur", "Suppression échouée", e.getMessage());
        }
    }

    private void afficherAlerte(String titre, String header, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(header);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void afficherAlerte(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}

