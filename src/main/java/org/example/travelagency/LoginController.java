package org.example.travelagency;

import Services.Impl.AgentServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController{

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;


    @FXML
    protected void loginAgent() throws IOException {
        AgentServiceImpl agentService = new AgentServiceImpl();
        String login = loginField.getText();
        String password = passwordField.getText();
        boolean isAuthenticated = false;

        try {
            isAuthenticated = agentService.Login(login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isAuthenticated) {
            System.out.println("Login successful");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setTitle("Dashboard global");
            stage.setScene(scene);

        } else {
            System.out.println("Login failed");
        }
    }
}
