package com.bikram.javafinal.Models;

public class SubjectWiseData {
    private int maths;
    private int science;
    private int english;
    private int social;
    private int nepali;

    public SubjectWiseData(int maths, int science, int social, int english , int nepali) {
        this.maths = maths;
        this.science = science;
        this.english = english;
        this.social = social;
        this.nepali = nepali;
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

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getNepali() {
        return nepali;
    }

    public void setNepali(int nepali) {
        this.nepali = nepali;
    }
}
