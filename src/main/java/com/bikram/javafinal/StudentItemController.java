package com.bikram.javafinal;

import com.bikram.javafinal.Models.Students;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class StudentItemController {
    @FXML
    private Label lroll;

    @FXML
    private Label lname;

    @FXML
    private Label lscience;

    @FXML
    private Label lmaths;

    @FXML
    private Label lsocial;

    @FXML
    private Label lenglish;


    @FXML
    private Label lnepali;

    @FXML
    private Button lResult;

    public void setData(Students s){
        lscience.setText(Integer.toString(s.getScience()));
        lname.setText(s.getName());
        lmaths.setText(Integer.toString(s.getMaths()));
        lenglish.setText(Integer.toString(s.getEnglish()));
        lnepali.setText(Integer.toString(s.getNepali()));
        lroll.setText(Integer.toString(s.getRoll()));
        lsocial.setText(Integer.toString(s.getSocial()));
        lResult.setText(s.isPass()?"Pass":"Fail");
        lResult.setStyle(s.isPass()?"-fx-border-color: #2A73FF; -fx-border-radius: 20; -fx-background-color: transparent;":"-fx-border-color: red; -fx-border-radius: 20; -fx-background-color: transparent;");
    }
}
