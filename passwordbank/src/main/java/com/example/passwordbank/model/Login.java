package com.example.passwordbank.model;

import java.util.Date;

public class Login {

    private String userName;
    private Password password;
    private Date creationDate;
    private Date lastEditDate;




    public Login(String userName, String password) {
        setUserName(userName);
        setPassword(password);

        Date date = new Date();
        setCreationDate(date);
        setLastEditDate(date);
    }




    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Password getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = new Password(password);
        // this.password.setPass(password);
    }


    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Date getLastEditDate() {
        return lastEditDate;
    }
    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }


    
}
