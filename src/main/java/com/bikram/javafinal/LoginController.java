package com.bikram.javafinal;

import com.bikram.javafinal.Models.AppConstants;
import com.bikram.javafinal.Models.LoginResult;
import com.bikram.javafinal.dbconnections.Dbconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label signinMessage;
    @FXML
    private TextArea username;
    @FXML
    private TextArea password;

    @FXML
    protected  void onlogin(ActionEvent e) throws  IOException {
        String un = username.getText();
        String pw = password.getText();
        LoginResult isLoggedIn = Dbconnection.login(un,pw);
        if (isLoggedIn.isLoggedIn()){
            AppConstants.setLoggedInUser(isLoggedIn.getUsername());
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Home-view.fxml"));
            Scene signupScene = new Scene(fxmlLoader.load());
            Scene oldSignin = ((Node)e.getSource()).getScene();
            Stage stage = (Stage) (oldSignin.getWindow());
            stage.setScene(signupScene);
        } else {
            signinMessage.setText("Login Failed");
            System.out.println("Login failed");
        }
    }

    @FXML
    protected void onSignupClick( ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("signup-view.fxml"));
        Scene signupScene = new Scene(fxmlLoader.load());
        Scene oldSignin = ((Node)e.getSource()).getScene();
        Stage stage = (Stage) (oldSignin.getWindow());
        stage.setScene(signupScene);
        System.out.println("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signinMessage.setText(AppConstants.getSignInMessage());
    }
}