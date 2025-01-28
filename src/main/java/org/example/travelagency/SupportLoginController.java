package org.example.travelagency;


import Entities.SupportTech;
import Services.Impl.SupportTechServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SupportLoginController {

    private final SupportTechServiceImpl supportTechService = new SupportTechServiceImpl();
    @FXML
    private TextField code;
    @FXML
    private TextField login;
    @FXML
    private TextField mdp;
    @FXML
    private Button validBtn;

    @FXML
    void connecter(ActionEvent event) {
        // Récupération des valeurs des champs
        String codeText = code.getText().trim();
        String loginText = login.getText().trim();
        String mdpText = mdp.getText().trim();

        try {
            // Vérification du code
            if (codeText.isEmpty()) {
                afficherErreur("Le champ code est obligatoire.");
                return;
            }

            int codeValue = Integer.parseInt(codeText);
            SupportTech support = supportTechService.checkSupportByCode(codeValue);

            if (support == null) {
                afficherErreur("Aucun support technique trouvé avec ce code.");
                return;
            }

            // Vérification du login et du mot de passe
            if (!support.getLogin().equals(loginText) || !support.getMotDePasse().equals(mdpText)) {
                afficherErreur("Login ou mot de passe incorrect.");
                return;
            }

            // Connexion réussie
            afficherSucces("Connexion réussie !");
            // TODO : Passer à la page suivante ou charger un nouveau contenu

        } catch (NumberFormatException e) {
            afficherErreur("Le code doit être un entier.");
        } catch (Exception e) {
            afficherErreur("Une erreur est survenue : " + e.getMessage());
        }
    }

    // Méthode pour afficher une erreur
    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour afficher un succès
    private void afficherSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
