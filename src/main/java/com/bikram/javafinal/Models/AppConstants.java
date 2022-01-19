package com.bikram.javafinal.Models;

public class AppConstants {
    public static String loggedInUser = null;
    public static String signInMessage = null;
    public static String signUpMessage = null;

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(String loggedInUser) {
        AppConstants.loggedInUser = loggedInUser;
    }

    public static String getSignInMessage() {
        return signInMessage;
    }

    public static void setSignInMessage(String signInMessage) {
        AppConstants.signInMessage = signInMessage;
    }

    public static String getSignUpMessage() {
        return signUpMessage;
    }

    public static void setSignUpMessage(String signUpMessage) {
        AppConstants.signUpMessage = signUpMessage;
    }
}
