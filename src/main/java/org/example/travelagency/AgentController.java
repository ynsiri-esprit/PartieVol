package org.example.travelagency;

import Services.Impl.AgentServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AgentController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void loginAgent() {
        AgentServiceImpl A=new AgentServiceImpl();
        String login = loginField.getText();
        String password = passwordField.getText();
        boolean b=false;
        try {
            b=A.Login(login,password);
        }
        catch (Exception e) {}
if(b==true){
    System.out.println("true");}
        else
    System.out.println("false");
    }

}
