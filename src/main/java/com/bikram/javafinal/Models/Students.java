package com.bikram.javafinal.Models;

public class Students extends Human {
    private final int roll;
    private int maths;
    private int science;
    private int social;
    private int english;
    private int nepali;

    public Students(int roll, String name, int maths, int science, int social, int english, int nepali) {
        this.name = name;
        this.roll = roll;
        this.maths = maths;
        this.science = science;
        this.social = social;
        this.english = english;
        this.nepali = nepali;
    }

    public Students(int roll, String name) {
        this.name = name;
        this.roll = roll;
    }

    public int getRoll() {
        return roll;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getNepali() {
        return nepali;
    }

    public void setNepali(int nepali) {
        this.nepali = nepali;
    }
}
