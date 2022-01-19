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
import javafx.scene.chart.*;
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
    private Label totalStudent;

    @FXML
    private Label passPercent;

    @FXML
    private Label failed;

    @FXML
    private Label highestPercent;

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
    private Pane pnlBarChart;

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
    LineChart<String, Number> linechart;


    @FXML
    BarChart<String, Number> bc;

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
    private void setData(){
        pnItems.getChildren().clear();
        linechart.getData().clear();
        bc.getData().clear();

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
        XYChart.Series<String,Number>series=new XYChart.Series<String,Number>();
        series.setName("Maths");
        series.getData().add(new XYChart.Data<String,Number>("Jan",200));
        series.getData().add(new XYChart.Data<String,Number>("Feb",350));
        series.getData().add(new XYChart.Data<String,Number>("March",420));
        series.getData().add(new XYChart.Data<String,Number>("April",550));
        XYChart.Series<String,Number>series1=new XYChart.Series<String,Number>();
        series1.setName("Science");
        series1.getData().add(new XYChart.Data<String,Number>("Jan",20));
        series1.getData().add(new XYChart.Data<String,Number>("Feb",370));
        series1.getData().add(new XYChart.Data<String,Number>("March",320));
        series1.getData().add(new XYChart.Data<String,Number>("April",500));
        XYChart.Series<String,Number>series2=new XYChart.Series<String,Number>();
        series2.setName("Social");
        series2.getData().add(new XYChart.Data<String,Number>("Jan",250));
        series2.getData().add(new XYChart.Data<String,Number>("Feb",450));
        series2.getData().add(new XYChart.Data<String,Number>("March",320));
        series2.getData().add(new XYChart.Data<String,Number>("April",250));

        linechart.getData().add(series);
        linechart.getData().add(series1);
        linechart.getData().add(series2);

//        xAxis.setLabel("Percent");
//        xAxis.setTickLabelRotation(90);
//        yAxis.setLabel("Performance");
//        xAxis.setLabel("Percent");
//        xAxis.setTickLabelRotation(90);
//        yAxis.setLabel("Performance");

//        XYChart.Series<String, Number> series3 = new XYChart.Series<String,Number>();
//        series1.getData().add(new XYChart.Data<String,Number>( "Test", 80));
        bc.getData().add(series1);
        bc.getData().add(series2);
        bc.getData().add(series);
        int total,fail,pass;
        float highest;
        try {
            total = Dbconnection.getTotalStudents();
        } catch (Exception e) {
            total = 0;
            e.printStackTrace();
        }
        try {
            pass = (int)(( (float)Dbconnection.getTotalPassStudents() /(float) total)*100);
            fail = total - Dbconnection.getTotalPassStudents();
        } catch (Exception e) {
            pass =0;
            fail = 0;
            e.printStackTrace();
        }
        try {
            highest = ((float)Dbconnection.getHighestPercentage()/500)*100;
        } catch (Exception e) {
            highest = 0.0f;
            e.printStackTrace();
        }
        totalStudent.setText(Integer.toString(total,10));
        passPercent.setText(Integer.toString(pass) + "%");
        failed.setText(Integer.toString(fail));
        highestPercent.setText(Float.toString(highest));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    setData();
    }


    public void handleClicks(ActionEvent actionEvent) {
        setData();
        if (actionEvent.getSource() == btnAddStudent) {
//            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlAddStudent.setVisible(true);
            pnlOverview.setVisible(false);
            pnlBarChart.setVisible(false);
            pnlAddStudent.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlAddStudent.setVisible(false);
            pnlOverview.setVisible(false);
            pnlBarChart.setVisible(true);
            pnlBarChart.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
//            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlAddStudent.setVisible(false);
            pnlOverview.setVisible(true);
            pnlBarChart.setVisible(false);
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
//            pnlBarChart.setStyle("-fx-background-color : #464F67");
            pnlBarChart.toFront();
        }
    }
}