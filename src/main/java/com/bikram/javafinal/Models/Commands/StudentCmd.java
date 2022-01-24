package com.bikram.javafinal.Models.Commands;

import com.bikram.javafinal.Models.Command;
import com.bikram.javafinal.Models.Students;
import com.bikram.javafinal.dbconnections.Dbconnection;
import javafx.collections.ObservableList;

public class StudentCmd implements Command {
    public static void run(String[] arg) {
        String invalid = "Invalid Syntax! \n The correct syntax are: \n student i [roll] \n student list";
        if (arg.length <2){
            System.out.println(invalid);
            return;
        }
        if(arg[1].equals("i")){
            try {
                int index = Integer.parseInt(arg[2]);
                Students res = Dbconnection.getStudent(index);
                res.printData();
            }catch (Exception e){
                System.out.println("Couldn't fetch student information. Please check if valid roll number is provided.");
            }
        } else if(arg[1].equals("list")){
            System.out.println("Gives list of all students");
            try {
                ObservableList<Students> studentList = Dbconnection.getAllStudents();
                for(int i = 0; i < studentList.size(); i++){
                    Students s = studentList.get(i);
                    s.printData();
                }
            }catch (Exception e){
                System.out.println("Unable to fetch student informations");
            }
        } else {
            System.out.println(invalid);
        }
    }
}
