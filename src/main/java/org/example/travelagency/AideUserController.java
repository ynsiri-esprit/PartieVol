package org.example.travelagency;

import Entities.Aide;
import Services.Impl.AideServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AideUserController {

    private final AideServiceImpl aideService = new AideServiceImpl();
    private final ObservableList<Aide> faqList = FXCollections.observableArrayList();
    @FXML
    private TextField txtRecherche;
    @FXML
    private TableView<Aide> tableFAQ;
    @FXML
    private TableColumn<Aide, String> colQuestion;
    @FXML
    private TableColumn<Aide, String> colReponse;
    @FXML
    private TextField txtNouvelleQuestion;

    @FXML
    public void initialize() throws SQLException {
        colQuestion.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        colReponse.setCellValueFactory(cellData -> cellData.getValue().reponseProperty());
        loadFAQs();
    }

    public void resetPage() throws SQLException {
        txtRecherche.clear();
        txtNouvelleQuestion.clear();
        loadFAQs();
    }

    private void loadFAQs() throws SQLException {
        faqList.setAll(aideService.getAll());
        tableFAQ.setItems(faqList);
    }

    @FXML
    private void rechercherQuestion() {
        String searchText = txtRecherche.getText().toLowerCase();
        if (searchText.isEmpty()) {
            tableFAQ.setItems(faqList);
        } else {
            ObservableList<Aide> filteredList = faqList.filtered(aide ->
                    aide.getQuestion().toLowerCase().contains(searchText)
            );
            tableFAQ.setItems(filteredList);
        }
    }

    @FXML
    private void envoyerQuestion() throws SQLException {
        String question = txtNouvelleQuestion.getText().trim();
        if (!question.isEmpty()) {
            Aide newAide = new Aide(0, question, "En attente");
            aideService.ajouter(newAide);
            loadFAQs();
            txtNouvelleQuestion.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez entrer une question valide.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void test() {
    }
}

