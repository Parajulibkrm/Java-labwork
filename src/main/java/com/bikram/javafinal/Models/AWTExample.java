package com.bikram.javafinal.Models;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// class AWTExample2 directly creates instance of Frame class
public class AWTExample implements WindowListener {
    Frame f;
    // initializing using constructor
    public AWTExample(){


        // creating a Frame
        f = new Frame();
        f.addWindowListener(this);

        // creating a Label
        Label l = new Label("Employee id:");

        // creating a Button
        Button b = new Button("Submit");

        // creating a TextField
        TextField t = new TextField();

        // setting position of above components in the frame
        l.setBounds(20, 80, 80, 30);
        t.setBounds(20, 100, 80, 30);
        b.setBounds(100, 100, 80, 30);

        // adding components into frame
        f.add(b);
        f.add(l);
        f.add(t);

        // frame size 300 width and 300 height
        f.setSize(400,300);

        // setting the title of frame
        f.setTitle("Employee info");

        // no layout
        f.setLayout(null);

        // setting visibility of frame
        f.setVisible(true);
        System.out.println("Started");
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
    f.dispose();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}