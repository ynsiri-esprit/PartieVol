package org.example.travelagency;

import Entities.Agent;
import Services.Impl.AgentServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GererAgentsController implements Initializable {

    AgentServiceImpl agentService = new AgentServiceImpl();
    List<Agent> agents = agentService.getAll();
    @FXML
    private ListView<String> listeAgents;

    public GererAgentsController() throws SQLException {
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        listeAgents.getItems().addAll(agents.stream()
                .map(agent -> agent.getNom()+" "+agent.getPrenom()).toList());
    }
}
