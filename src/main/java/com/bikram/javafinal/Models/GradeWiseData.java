package com.bikram.javafinal.Models;

public class GradeWiseData {
    private int Ap;
    private int A;
    private int Bp;
    private int B;
    private int Cp;
    private int C;
    private int E;
    private int D;
    private int N;

    public GradeWiseData(int ap, int a, int bp, int b, int cp, int c, int d, int e, int n) {
        Ap = ap;
        A = a;
        Bp = bp;
        B = b;
        Cp = cp;
        C = c;
        E = e;
        D = d;
        N = n;
    }

    public int getAp() {
        return Ap;
    }

    public void setAp(int ap) {
        Ap = ap;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getBp() {
        return Bp;
    }

    public void setBp(int bp) {
        Bp = bp;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getCp() {
        return Cp;
    }

    public void setCp(int cp) {
        Cp = cp;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getN() {
        return N;
    }

    public void setF(int f) {
        N = f;
    }
}
