package com.bikram.javafinal;

import com.bikram.javafinal.Models.Students;
import javafx.fxml.FXML;
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

    public void setData(Students s){
        lscience.setText(Integer.toString(s.getScience()));
        lname.setText(s.getName());
        lmaths.setText(Integer.toString(s.getMaths()));
        lenglish.setText(Integer.toString(s.getEnglish()));
        lnepali.setText(Integer.toString(s.getNepali()));
        lroll.setText(Integer.toString(s.getRoll()));
        lsocial.setText(Integer.toString(s.getSocial()));
    }
}
