package com.bikram.javafinal.Models;

import com.bikram.javafinal.Models.Commands.StudentCmd;

import java.io.IOException;

public class CommandsHandler {
    public static void handleCmd(String s) throws IOException {
        String[] cmd = s.split(" ");
        switch (cmd[0]) {
            case "cls":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case "help":
                System.out.println("All available Commands are: \n cls \n help \n student");
                break;
            case "student":
                StudentCmd.run(cmd);
                break;
            default:
                break;
        }
    }
}
