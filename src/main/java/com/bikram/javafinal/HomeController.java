package com.bikram.javafinal;

import com.bikram.javafinal.Models.AppConstants;
import com.bikram.javafinal.Models.GradeWiseData;
import com.bikram.javafinal.Models.SubjectWiseData;
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
        SubjectWiseData avg;
        SubjectWiseData max;
        SubjectWiseData min;

        GradeWiseData maths;
        GradeWiseData science;
        GradeWiseData social;
        GradeWiseData english;
        GradeWiseData nepali;

        try {
            avg= Dbconnection.getAverageMarks();
        } catch (Exception e) {
            avg = new SubjectWiseData(0,0,0,0,0);
            e.printStackTrace();
        }
        try {
            max= Dbconnection.getHighestMarks();
        } catch (Exception e) {
            max = new SubjectWiseData(0,0,0,0,0);
            e.printStackTrace();
        }
        try {
            min= Dbconnection.getLowestMarks();
        } catch (Exception e) {
            min = new SubjectWiseData(0,0,0,0,0);
            e.printStackTrace();
        }

        try {
            maths= Dbconnection.getGradeWiseMarks("Maths");
        } catch (Exception e) {
            maths = new GradeWiseData(0,0,0,0,0,0,0,0,0);
            e.printStackTrace();
        }
        try {
            science= Dbconnection.getGradeWiseMarks("Science");
        } catch (Exception e) {
            science = new GradeWiseData(0,0,0,0,0,0,0,0,0);
            e.printStackTrace();
        }
        try {
            social= Dbconnection.getGradeWiseMarks("Social");
        } catch (Exception e) {
            social = new GradeWiseData(0,0,0,0,0,0,0,0,0);
            e.printStackTrace();
        }
        try {
            english= Dbconnection.getGradeWiseMarks("English");
        } catch (Exception e) {
            english = new GradeWiseData(0,0,0,0,0,0,0,0,0);
            e.printStackTrace();
        }
        try {
            nepali= Dbconnection.getGradeWiseMarks("Nepali");
        }
        catch (Exception e) {
            nepali = new GradeWiseData(0,0,0,0,0,0,0,0,0);
            e.printStackTrace();
        }


        XYChart.Series<String,Number> gradeScience=new XYChart.Series<String,Number>();
        gradeScience.setName("Science");
        gradeScience.getData().add(new XYChart.Data<String, Number>("N",science.getN()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("E",science.getE()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("D",science.getD()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("C",science.getC()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("C+",science.getCp()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("B+",science.getBp()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("B",science.getB()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("A",science.getA()));
        gradeScience.getData().add(new XYChart.Data<String, Number>("A+",science.getAp()));
        linechart.getData().add(gradeScience);

        XYChart.Series<String,Number> gradeMaths=new XYChart.Series<String,Number>();
        gradeMaths.setName("Maths");
        gradeMaths.getData().add(new XYChart.Data<String, Number>("N",maths.getN()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("E",maths.getE()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("D",maths.getD()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("C",maths.getC()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("C+",maths.getCp()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("B+",maths.getBp()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("B",maths.getB()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("A",maths.getA()));
        gradeMaths.getData().add(new XYChart.Data<String, Number>("A+",maths.getAp()));
        linechart.getData().add(gradeMaths);

        XYChart.Series<String,Number> gradeSocial=new XYChart.Series<String,Number>();
        gradeSocial.setName("Social");
        gradeSocial.getData().add(new XYChart.Data<String, Number>("N",social.getN()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("E",social.getE()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("D",social.getD()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("C",social.getC()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("C+",social.getCp()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("B+",social.getBp()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("B",social.getB()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("A",social.getA()));
        gradeSocial.getData().add(new XYChart.Data<String, Number>("A+",social.getAp()));
        linechart.getData().add(gradeSocial);

        XYChart.Series<String,Number> gradeEnglish=new XYChart.Series<String,Number>();
        gradeEnglish.setName("English");
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("N",english.getN()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("E",english.getE()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("D",english.getD()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("C",english.getC()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("C+",english.getCp()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("B+",english.getBp()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("B",english.getB()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("A",english.getA()));
        gradeEnglish.getData().add(new XYChart.Data<String, Number>("A+",english.getAp()));
        linechart.getData().add(gradeEnglish);

        XYChart.Series<String,Number> gradeNepali=new XYChart.Series<String,Number>();
        gradeNepali.setName("Nepali");
        gradeNepali.getData().add(new XYChart.Data<String, Number>("N",nepali.getN()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("E",nepali.getE()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("D",nepali.getD()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("C",nepali.getC()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("C+",nepali.getCp()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("B+",nepali.getBp()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("B",nepali.getB()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("A",nepali.getA()));
        gradeNepali.getData().add(new XYChart.Data<String, Number>("A+",nepali.getAp()));
        linechart.getData().add(gradeNepali);


//        linechart.getData().add(gradeMaths);
//        linechart.getData().add(series1);
//        linechart.getData().add(series2);

//        xAxis.setLabel("Percent");
//        xAxis.setTickLabelRotation(90);
//        yAxis.setLabel("Performance");
//        xAxis.setLabel("Percent");
//        xAxis.setTickLabelRotation(90);
//        yAxis.setLabel("Performance");

        XYChart.Series<String, Number> avgseries = new XYChart.Series<String,Number>();
        avgseries.setName("Average");
        avgseries.getData().add(new XYChart.Data<String,Number>("Maths",avg.getMaths()));
        avgseries.getData().add(new XYChart.Data<String,Number>("Science",avg.getScience()));
        avgseries.getData().add(new XYChart.Data<String,Number>("Social",avg.getSocial()));
        avgseries.getData().add(new XYChart.Data<String,Number>("English",avg.getEnglish()));
        avgseries.getData().add(new XYChart.Data<String,Number>("Nepali",avg.getNepali()));

        XYChart.Series<String, Number> maxseries = new XYChart.Series<String,Number>();
        maxseries.setName("Highest");
        maxseries.getData().add(new XYChart.Data<String,Number>("Maths",max.getMaths()));
        maxseries.getData().add(new XYChart.Data<String,Number>("Science",max.getScience()));
        maxseries.getData().add(new XYChart.Data<String,Number>("Social",max.getSocial()));
        maxseries.getData().add(new XYChart.Data<String,Number>("English",max.getEnglish()));
        maxseries.getData().add(new XYChart.Data<String,Number>("Nepali",max.getNepali()));

        XYChart.Series<String, Number> minseries = new XYChart.Series<String,Number>();
        minseries.setName("Lowest");
        minseries.getData().add(new XYChart.Data<String,Number>("Maths",min.getMaths()));
        minseries.getData().add(new XYChart.Data<String,Number>("Science",min.getScience()));
        minseries.getData().add(new XYChart.Data<String,Number>("Social",min.getSocial()));
        minseries.getData().add(new XYChart.Data<String,Number>("English",min.getEnglish()));
        minseries.getData().add(new XYChart.Data<String,Number>("Nepali",min.getNepali()));


//        series1.getData().add(new XYChart.Data<String,Number>( "Test", 80));
        bc.getData().add(avgseries);
        bc.getData().add(maxseries);
        bc.getData().add(minseries);

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