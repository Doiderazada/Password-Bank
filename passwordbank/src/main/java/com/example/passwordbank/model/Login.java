package com.example.passwordbank.model;

import java.util.Date;

import java.io.Serializable;

public final class Login implements Serializable, Comparable<Login> {

    @SuppressWarnings("unused")
    private static final long serialVersion = 1000000011L;

    private String identifier;
    private String userName;
    private Password password;
    private Date creationDate;
    private Date lastEditDate;
    private int useCount;


    public Login() {
        Date date = new Date();
        setCreationDate(date);
    }

    public Login(String userName, String password) {
        Date date = new Date();
        setCreationDate(date);

        setUserName(userName);
        setPassword(password);
    }


    public String getIdentifier() {
        return this.identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        Date lastEdited = new Date();
        setLastEditDate(lastEdited);
    }


    public Date getCreationDate() {
        return creationDate;
    }
    private void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Date getLastEditDate() {
        return lastEditDate;
    }
    private void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }


    public int getUseCount() {
        return this.useCount;
    }
    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }
    public void increaseCount(){
        this.useCount++;
    }

    
    @Override
    public int compareTo(Login login) {
        return this.getIdentifier().compareTo(login.getIdentifier());
    }
}