package org.example.travelagency;
import Entities.Utilisateur;
import Services.Impl.UtilisateurServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class SignInController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private UtilisateurServiceImpl utilisateurService = new UtilisateurServiceImpl();

    @FXML
    public void handleLogin(ActionEvent event) {
        String email = loginField.getText().trim();
        String password = passwordField.getText();

        // Vérification des champs vides
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs !", Alert.AlertType.ERROR);
            return;
        }
        // Vérification des identifiants
        UtilisateurServiceImpl utilisateurService = new UtilisateurServiceImpl();
        Utilisateur utilisateur = utilisateurService.seConnecter(email, password);
        if (utilisateur != null) {
            showAlert("Succès", "Connexion réussie !", Alert.AlertType.INFORMATION);
            redirigerUtilisateur(utilisateur);
        } else {
            showAlert("Erreur", "Email ou mot de passe incorrect !", Alert.AlertType.ERROR);
        }

    }
    private void showAlert(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void redirigerUtilisateur(Utilisateur utilisateur) {
        switch (utilisateur.getRole()) {
            case AGENT:
                System.out.println("Redirection vers l'interface Agent...");
                break;
            case CLIENT:
                System.out.println("Redirection vers l'interface Client...");
                break;
            case SUPPORT_TECH:
                System.out.println("Redirection vers l'interface Support Technique...");
                break;
            default:
                System.out.println("Aucun rôle défini !");
        }
    }
}
