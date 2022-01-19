package com.bikram.javafinal;

import com.bikram.javafinal.Models.AppConstants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private Label welcomeMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeMessage.setText("Welcome "+ AppConstants.getLoggedInUser() + "!");
    }
}
