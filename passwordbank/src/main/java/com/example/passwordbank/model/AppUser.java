package com.example.passwordbank.model;

import java.io.Serializable;

public class AppUser implements Serializable {

    @SuppressWarnings("unused")
    private static final long serialVersion = 1000000001L; 

    private String mainEmail;
    private Password password;
    private String username;
    private String altEmail;
    private String mobileNumber;
    private String question1;
    private String question2;
    private String question3;
    private String answer1;
    private String answer2;
    private String answer3;
    private boolean darkMode;
    private boolean stayLoggedIn;
    private boolean haveRecoverInfo;




    public AppUser() {    
    }

    

    public AppUser(String mainEmail, String password, String username, String altEmail, String mobileNumber, String question1,
                   String question2, String question3, String answer1, String answer2, String answer3, boolean darkMode) {
        setMainEmail(mainEmail);
        setPassword(password);
        setUsername(username);
        setMobileNumber(mobileNumber);
        setQuestion1(question1);
        setQuestion2(question2);
        setQuestion3(question3);
        setAnswer1(answer1);
        setAnswer2(answer2);
        setAnswer3(answer3);
        setDarkMode(darkMode);
    }












    public String getMainEmail() {
        return mainEmail;
    }
    public void setMainEmail(String userEmail) {
        this.mainEmail = userEmail;
    }



    public Password getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = new Password(password);
    }



    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }



    public String getAltEmail() {
        return this.altEmail;
    }
    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public String getQuestion1() {
        return question1;
    }
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }


    public String getQuestion2() {
        return question2;
    }
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }


    public String getQuestion3() {
        return question3;
    }
    public void setQuestion3(String question3) {
        this.question3 = question3;
    }


    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }


    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }


    public String getAnswer3() {
        return answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    
    public boolean isDarkMode() {
        return darkMode;
    }
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }



    public boolean isStayLoggedIn() {
        return this.stayLoggedIn;
    }
    public void setStayLogged(boolean stayLogged) {
        this.stayLoggedIn = stayLogged;
    }


    public boolean isRecoverInfo() {
        return haveRecoverInfo;
    }
    public void setRecoverInfo(boolean recoverInfo) {
        this.haveRecoverInfo = recoverInfo;
    }
}