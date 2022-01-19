package com.bikram.javafinal.Models.Commands;

import com.bikram.javafinal.Models.Command;

public class StudentCmd implements Command {
    public static void run(String[] arg) {
        if(arg[1].equals("i")){
            System.out.println("Gives student information for Id 1");
         } else if(arg[1].equals("list")){
            System.out.println("Gives list of all students");
        } else {
            System.out.println("Invalid Syntax! \n The correct syntax are: \n student i [id] \n student list");
        }
    }
}
