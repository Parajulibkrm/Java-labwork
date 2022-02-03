package com.bikram.javafinal.Models;

import com.bikram.javafinal.Models.Commands.StudentCmd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CommandsHandler {
    public static void handleCmd(String s) throws IOException {
        String error = "All available Commands are: \n cls \n help \n student";
        String[] cmd = s.split(" ");
        switch (cmd[0]) {
            case "chat":
                new SocketServer();
            case "cls":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case "help":
                System.out.println(error);
                break;
            case "student":
                StudentCmd.run(cmd);
                break;
            default:
                System.out.println("Invalid command ! \n" + error);
                break;
        }
    }
}
