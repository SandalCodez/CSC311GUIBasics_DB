package com.example.module03_basicgui_db_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SIgnIn_Controller {
    @FXML
private TextField userField;

    @FXML
    private PasswordField passField;
    @FXML
    private Button signInBtn;
    @FXML
    private Button registerBtn1;

    @FXML
    private ToggleButton darkModeBtn;


    //Method to allow the user to go beyond the sign in screen
    @FXML
    protected void handleLogin(ActionEvent event) {
        String user = userField.getText();
        String pass = passField.getText();

        //Authentication does not connect with the DB
        if (user.equals("admin") && pass.equals("pass")) {
            DB_Application.getInstance().changeScene();
        } else {
            System.out.println("Invalid login!");
        }
    }
    @FXML
    void handleRegister(ActionEvent event) {
    ConnDBOpsCopy connection = new ConnDBOpsCopy();
    connection.connectToDatabase();
    }

    //Method to switch to and from dark mode within the sign in screen
    @FXML
    void handleDarkMode(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();

        //switch to a different css file with dark features
        String darkStyle = getClass().getResource("styling/dark.css").toExternalForm();

        //handle the toggle off
        if (scene.getStylesheets().contains(darkStyle)) {
            scene.getStylesheets().remove(darkStyle);
            System.out.println("Switched to light mode.");
        } else {
            scene.getStylesheets().add(darkStyle);
            System.out.println("Switched to dark mode.");
        }
    }
}
