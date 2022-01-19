package com.bikram.javafinal.Models;

public class LoginResult {
    private boolean loggedIn = false;
    private String username;

    public LoginResult(boolean loggedIn, String username) {
        this.loggedIn = loggedIn;
        this.username = username;
    }

    public LoginResult(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
