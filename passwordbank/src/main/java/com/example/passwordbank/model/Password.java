package com.example.passwordbank.model;

import java.util.Random;

public class Password {

    private String plainPass;
    private String protectedPass;
    private String salt;
    private String pepper;
    private int[] pattern;

    

    public Password(String plainPass) {
        setPass(plainPass);
    }



    public void setPass(String pass) {
        this.plainPass = pass;
    }
    private String getPass() {
        return this.plainPass;
    }

    private void setSalt(String salt) {
        this.salt = salt;
    }
    private void setPattern(int[] pattern) {
        this.pattern = pattern;
    }
    private void setPepper(String pepper) {
        this.pepper = pepper;
    }
    private void setProtectedPass(String pass) {
        this.protectedPass = pass;
    }




    private void createPattern() {
        int[] randoms = new int[plainPass.length()];
        Random random = new Random();
        for (int i = 0; i < plainPass.length(); i++) {
            randoms[i] = random.nextInt(1, 15);
        }
        setPattern(randoms);
    }

    private void doSalting() {
        int val = pattern[0];
        Random random = new Random();
        char[] charSalt = new char[val];
        for (int i = 0; i < val; i++) {
            int r = random.nextInt(33, 256);
            charSalt[i] = (char) r;
        }
        String salting = String.valueOf(charSalt);
        setSalt(salting);
    }

    private void doPeppering() {
        int val = pattern[1];
        Random random = new Random();
        char[] charPepp = new char[val];
        for (int i = 0; i < val; i++) {
            int rVal = random.nextInt(33, 256);
            charPepp[i] = (char) rVal;
        }
        String peppering = String.valueOf(charPepp);
        setPepper(peppering);
    }

    private void doPseudoHashing() {
        int len1 = plainPass.length();

        byte[] passBytes = new byte[len1];
        char[] charArray = new char[len1];

        passBytes = plainPass.getBytes();


        for (int i = 0; i < passBytes.length; i++) {
            int passVal = passBytes[i] * pattern[i];
            char passChar = (char) passVal;
            charArray[i] = passChar;
        }

        String charString = String.valueOf(charArray);
        setProtectedPass(salt + charString + pepper);
    }

    


    public void protectPassword() {
        createPattern();
        doSalting();
        doPeppering();
        doPseudoHashing();
        plainPass = null;
    }

    public void retrievePass() {
        int saltLen = pattern[0];
        int peppLen = pattern[1];

        String rawPass = protectedPass.substring(saltLen, protectedPass.length()-peppLen);
        char[] rawChar = new char[rawPass.length()];

        for (int i = 0; i < rawPass.length(); i++) {
            int val = (rawPass.charAt(i) / pattern[i]);
            rawChar[i] = (char) val;
        }

        String finalPass = String.valueOf(rawChar);

        plainPass = finalPass;
    }




    public final static boolean comparePasswords(String candidate, Password password) {
        String pass = password.getPass();

        return candidate.equals(pass);
    }
}