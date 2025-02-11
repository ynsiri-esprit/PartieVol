package org.example.travelagency;

import Entities.Agent;
import Entities.Utilisateur;
import Services.Impl.AgentServiceImpl;
import Services.Impl.UtilisateurServiceImpl;
import enums.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class GererAgentsController implements Initializable {

    private UtilisateurServiceImpl userService = new UtilisateurServiceImpl();

    @FXML
    private TableView<Utilisateur> tableAgents;

    @FXML
    private TableColumn<Utilisateur, String> colCIN, colNom, colPrenom, colDateNaissance, colTelephone, colEmail, colMotDePasse;

    @FXML
    private TextField txtCIN, txtNom, txtPrenom, txtEmail, txtTelephone;

    @FXML
    private DatePicker dateNaissancePicker;

    @FXML
    private TextField txtMotDePasse;

    @FXML
    private Button btnAjouter, btnModifier, btnSupprimer;

    public GererAgentsController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation des colonnes du tableau

        colNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        colPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colDateNaissance.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty().asString());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colTelephone.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
        colMotDePasse.setCellValueFactory(cellData -> cellData.getValue().motDePasseProperty());
        chargerData();
        // Ajout d'un listener
        tableAgents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtNom.setText(newValue.getNom());
                txtPrenom.setText(newValue.getPrenom());
                txtEmail.setText(newValue.getEmail());
                txtTelephone.setText(newValue.getTelephone());
                txtMotDePasse.setText(newValue.getMotDePasse());
                dateNaissancePicker.setValue(newValue.getDateNaissance());
            }
        });
    }

    private void chargerData() {
        // Charger la liste des agents
        List<Utilisateur> agents = userService.getAllUtilisateurs();
        tableAgents.getItems().setAll(agents);
    }

    private void clearForm() {
        txtCIN.clear();
        txtNom.clear();
        txtPrenom.clear();
        txtEmail.clear();
        txtTelephone.clear();
        txtMotDePasse.clear();
        dateNaissancePicker.setValue(null);
    }

//TODO : recreer la table users et maj l'insertion
    @FXML
    private void ajouterAgent() {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String email = txtEmail.getText();
        String telephone = txtTelephone.getText();
        String motDePasse = txtMotDePasse.getText();

        // Récupérer la date de naissance sélectionnée
        LocalDate dateNaissance = dateNaissancePicker.getValue();
        if (dateNaissance != null) {
            // Créer un agent
            Utilisateur newAgent = new Utilisateur(0, nom, prenom,motDePasse, dateNaissance, telephone,email, Role.AGENT);

            // Ajouter l'agent via le service
            userService.ajouterUtilisateur(newAgent);

            // Afficher une alerte de succès
            showAlert(Alert.AlertType.INFORMATION, "Succès", null,
                    "L'agent a été ajouté avec succès !\nLes coordonnées ont été envoyées par email.");

            // Envoi de l'email avec les coordonnées de l'agent
            userService.sendEmailToAgent(newAgent);
            clearForm();
            chargerData();

        } else {
            // Si aucune date n'est sélectionnée
            System.out.println("Veuillez sélectionner une date de naissance.");
            // Afficher une alerte de warning
            showAlert(Alert.AlertType.WARNING, "Attention", null, "Veuillez sélectionner une date de naissance.");
        }
    }


    @FXML
    private void modifierAgent() {
        Utilisateur selectedAgent = tableAgents.getSelectionModel().getSelectedItem();
        if (selectedAgent != null) {
            // Récupérer les valeurs des champs
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String email = txtEmail.getText();
            String telephone = txtTelephone.getText();
            String motDePasse = txtMotDePasse.getText();

            // Récupérer la date de naissance sélectionnée
            LocalDate dateNaissance = dateNaissancePicker.getValue();

            if (dateNaissance != null) {
                // Mettre à jour l'agent
                selectedAgent.setNom(nom);
                selectedAgent.setPrenom(prenom);
                selectedAgent.setDateNaissance(dateNaissance);
                selectedAgent.setEmail(email);
                selectedAgent.setTelephone(telephone);
                selectedAgent.setMotDePasse(motDePasse);

                // Modifier l'agent via le service
                userService.modifierUtilisateur(selectedAgent);
                showAlert(Alert.AlertType.INFORMATION, "Succès", null
                        , "Agent mis à jour avec succès !");
                clearForm();
                chargerData();
            } else {
                System.out.println("Veuillez sélectionner une date de naissance.");
            }
        } else {
            System.out.println("Aucun agent sélectionné.");
        }
    }


    @FXML
    private void supprimerAgent() {
        Utilisateur selectedAgent = tableAgents.getSelectionModel().getSelectedItem();
        if (selectedAgent == null) {
            showAlert(Alert.AlertType.WARNING, "Attention", null,
                    "Veuillez sélectionner un agent à supprimer.");
            return;
        }

        // Créer une alerte de confirmation avant la suppression
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de Suppression");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet agent ?");
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                userService.supprimerUtilisateur(selectedAgent.getId());
                showAlert(Alert.AlertType.INFORMATION, "Succès", null,
                        "Agent supprimé avec succès");
                clearForm();
                chargerData();
            } else {
                System.out.println("Suppression annulée");
            }
        });
    }


    @FXML
    private void logout() {
        // Implémentation de la déconnexion
        // Par exemple, fermer la fenêtre actuelle ou rediriger vers une autre vue
    }

    @FXML
    private void goToFAQ() {
        // Implémentation de la redirection vers l'interface des FAQ
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
