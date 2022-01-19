package com.bikram.javafinal;

import com.bikram.javafinal.dbconnections.Dbconnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Application extends javafx.application.Application {
    Dbconnection db = new Dbconnection();
    @Override
    public void start(Stage stage) throws IOException {
//        stage.getIcons().add(new Image("../../../../resources/assets/logo.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner myObj = new Scanner(System.in);
                while(true){
                    System.out.println("Enter name, age and salary:");
                    String name = myObj.nextLine();
                    System.out.println(name);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        launch();

    }
}