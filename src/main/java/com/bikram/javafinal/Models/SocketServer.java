package com.bikram.javafinal.Models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    SocketServer(){
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(6666);
            System.out.println("Socket server started at port 6666");
            Socket soc = ss.accept();
            boolean shouldContinue = true;
            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
            Scanner sc = new Scanner(System.in);
            //create new Thread
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String message = null;

                        try {
                            DataInputStream dis = new DataInputStream(soc.getInputStream());
                            message = (String) dis.readUTF();
                            System.out.println(message);
                        } catch (IOException e) {
                            return;

                        }

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
        } catch (IOException e) {
            System.out.println("Error with creating socket server. " + e.getMessage());
        }
    }
}
