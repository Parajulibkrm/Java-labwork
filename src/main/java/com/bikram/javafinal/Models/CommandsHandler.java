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
        if (s.equals("chat")) {
            ServerSocket ss = null;
            try {
                ss = new ServerSocket(6666);
                System.out.println("Socket server started at port 6666");
                Socket soc = ss.accept();
                boolean shouldContinue = true;
                DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
                DataInputStream dis = new DataInputStream(soc.getInputStream());
                Scanner sc = new Scanner(System.in);
                //create new Thread
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String message = null;
                            try {
                                message = (String) dis.readUTF();
                            } catch (IOException e) {
                                System.out.println("invalid message");
                            }
                            System.out.println(message);
                        }
                    }
                });
                t.start();
                while (shouldContinue) {
                    String messageToSend = sc.nextLine();
                    if (messageToSend.equals("exit")) {
                        shouldContinue = false;
                        t.interrupt();
                    }
                    dout.writeUTF("Bikram: "+messageToSend);
                    dout.flush();
                }
                dout.close();
                ss.close();
                return;
            } catch (IOException e) {
                System.out.println("Error with creating socket server. " + e.getMessage());
                return;
            }
        }
        switch (cmd[0]) {
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
