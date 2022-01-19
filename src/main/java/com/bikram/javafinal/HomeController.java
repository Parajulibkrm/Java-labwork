package com.bikram.javafinal;

import com.bikram.javafinal.Models.AppConstants;
import com.bikram.javafinal.Models.Students;
import com.bikram.javafinal.dbconnections.Dbconnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox pnItems = null;

    @FXML
    private Label loggedinUser;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlAddStudent;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @FXML
    private TextField name;

    @FXML
    private TextField roll;

    @FXML
    private TextField maths;

    @FXML
    private TextField science;

    @FXML
    private TextField social;

    @FXML
    private TextField english;

    @FXML
    private TextField nepali;

    @FXML
    public void handleSubmit(ActionEvent e){
        Students s = new Students(Integer.parseInt("0"+roll.getText()),name.getText(),Integer.parseInt("0"+maths.getText()),Integer.parseInt("0"+science.getText()),Integer.parseInt("0"+social.getText()),Integer.parseInt("0"+english.getText()),Integer.parseInt("0"+nepali.getText()));
        try {
            Dbconnection.registerStudent(s);
            name.setText("");
            roll.setText("");
            maths.setText("");
            science.setText("");
            social.setText("");
            english.setText("");
            nepali.setText("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedinUser.setText(AppConstants.getLoggedInUser());
        try {
            ObservableList<Students> studentList = Dbconnection.getAllStudents();
            Node[] nodes = new Node[studentList.size()];
            for (int i = 0; i < nodes.length; i++) {
                try {

                    final int j = i;
                    FXMLLoader fl = new FXMLLoader(getClass().getResource("Student-item.fxml"));
                    nodes[i] = fl.load();
                    StudentItemController s= fl.getController();
                    s.setData(studentList.get(i));

                    //give the items some effect

                    nodes[i].setOnMouseEntered(event -> {
                        nodes[j].setStyle("-fx-background-color : #0A0E3F");
                    });
                    nodes[i].setOnMouseExited(event -> {
                        nodes[j].setStyle("-fx-background-color : #02030A");
                    });
                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAddStudent) {
//            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlAddStudent.setVisible(true);
            pnlOverview.setVisible(false);
            pnlAddStudent.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
//            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlAddStudent.setVisible(false);
            pnlOverview.setVisible(true);
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }
}