package com.bikram.javafinal;

import com.bikram.javafinal.Models.AppConstants;
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

public class SignupController implements Initializable {
    @FXML
    private Label signupMessage;

    @FXML
    private TextArea username;
    @FXML
    private TextArea password;
    @FXML
    private TextArea name;

    @FXML
    protected void onSignUp(ActionEvent event){
        String n = name.getText();
        String un = username.getText();
        String pw = password.getText();
        try {
            Dbconnection.signUp(n,un,pw);
            AppConstants.setSignInMessage("Signup Successful. Please login now.");
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
            Scene signinScene = new Scene(fxmlLoader.load());
            Scene oldSignup = ((Node)event.getSource()).getScene();
            Stage stage = (Stage) (oldSignup.getWindow());
            stage.setScene(signinScene);
            System.out.println("Signup successful");
        } catch (Exception e) {
            AppConstants.setSignUpMessage("Signup Failed!");
            signupMessage.setText(AppConstants.getSignUpMessage());
            System.out.println("Error with creating account. " + e.getMessage());
        }
    }
    @FXML
    protected void onSigninClick( ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene signinScene = new Scene(fxmlLoader.load());
        Scene oldSignup = ((Node)e.getSource()).getScene();
        Stage stage = (Stage) (oldSignup.getWindow());
        stage.setScene(signinScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signupMessage.setText(AppConstants.getSignUpMessage());
    }
}
