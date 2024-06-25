package com.example.passwordbank.model;

public abstract class AppUser {

    private String userEmail;
    private String mobileNumber;
    private Password password;
    private String question1;
    private String question2;
    private String question3;
    private String answer1;
    private String answer2;
    private String answer3;
    private boolean darkMode;




    public AppUser() {    
    }

    public AppUser(String userEmail, String mobileNumber, String password, String question1,
            String question2, String question3, String answer1, String answer2, String answer3, boolean darkMode) {
        setUserEmail(userEmail);
        setPassword(password);
        setMobileNumber(mobileNumber);
        setQuestion1(question1);
        setQuestion2(question2);
        setQuestion3(question3);
        setAnswer1(answer1);
        setAnswer2(answer2);
        setAnswer3(answer3);
        setDarkMode(darkMode);
    }








    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public Password getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = new Password(password);
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




    

}
