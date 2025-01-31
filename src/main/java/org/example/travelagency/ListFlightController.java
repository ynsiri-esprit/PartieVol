package org.example.travelagency;

import Entities.Vol;
import Entities.TypeVol;
import Entities.VoyageOrganise;
import Services.Impl.VolServicesImpl;
import Services.Impl.VoyageOrganiseImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListFlightController {
    @FXML
    private TableView<Vol> tableView;
    @FXML
    private TableColumn<Vol, String> colDescription;
    @FXML
    private TableColumn<Vol, Float> colTarif;
    @FXML
    private TableColumn<Vol, String> colType;
    @FXML
    private TableColumn<Vol, String> colCompagnie;
    @FXML
    private TableColumn<Vol, String> colDepart;
    @FXML
    private TableColumn<Vol, String> colArrivee;
    @FXML
    private TableColumn<Vol, String> colHeureDepart;
    @FXML
    private TableColumn<Vol, String> colHeureArrivee;
    @FXML
    private TableColumn<Vol, Void> colAction;

    private final VolServicesImpl volService = new VolServicesImpl();

    public void initialize() {
        try {
            setupTable();
            addActionButtonsToTable();
            loadData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setupTable() {
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCompagnie.setCellValueFactory(new PropertyValueFactory<>("compagnie"));
        colDepart.setCellValueFactory(new PropertyValueFactory<>("aeroportDepart"));
        colArrivee.setCellValueFactory(new PropertyValueFactory<>("aeroportArrivee"));
        colHeureDepart.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
        colHeureArrivee.setCellValueFactory(new PropertyValueFactory<>("heureArrivee"));
    }

    private void addActionButtonsToTable() {
        colAction.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");
            private final Button deleteButton = new Button("Supprimer");
            private final HBox container = new HBox(5, editButton, deleteButton);

            {
                editButton.setStyle("-fx-background-color: #023047; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");

                editButton.setOnAction(event -> {
                    Vol vol = getTableView().getItems().get(getIndex());
                    onEdit(vol);
                });

                deleteButton.setOnAction(event -> {
                    Vol vol = getTableView().getItems().get(getIndex());
                    onDelete(vol);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(container);
                }
            }
        });
    }

    private void loadData() throws SQLException {

        VolServicesImpl voyageOrganiseImpl = new VolServicesImpl();
        List<Vol> voyages = voyageOrganiseImpl.getAll();
        ObservableList<Vol> observableVoyages = FXCollections.observableArrayList(voyages);
        tableView.setItems(observableVoyages);
    }

    private void onEdit(Vol vol) {
        System.out.println("Modifier vol: " + vol);
    }

    private void onDelete(Vol vol) {
        VolServicesImpl voyageOrganiseImpl = new VolServicesImpl();
        try {
            voyageOrganiseImpl.supprimer(vol);
            tableView.getItems().remove(vol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
