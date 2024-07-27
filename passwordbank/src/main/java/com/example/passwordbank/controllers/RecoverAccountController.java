package com.example.passwordbank.controllers;

import java.io.IOException;

import com.example.passwordbank.App;
import com.example.passwordbank.model.AppUser;

import animatefx.animation.SlideAnimation;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RecoverAccountController {

    @FXML private Button buttonBack;
    @FXML private Button buttonConfirm;
    @FXML private StackPane stackPaneMain;
    @FXML private Text textPresentation;


    @FXML private Button buttonConfirmEmail;
    @FXML private Label labelAltEmail;
    @FXML private Label labelMainEmail;
    @FXML private TextField tFieldAltEmail;
    @FXML private TextField tFieldMainEmail;
    @FXML private Text textAltEmail;
    @FXML private Text textEmalDesc;
    @FXML private Text textMainEmail;

    
    @FXML private Button buttonNextQuestion;
    @FXML private ChoiceBox<String> cBoxQuestions;
    @FXML private TextArea tAreaAnswers;
    @FXML private Text textDescQuest;

    
    @FXML private Button buttonNext;
    @FXML private Label labelEmail;
    @FXML private TextField tFieldEmail;
    @FXML private Text textDescription;
    @FXML private Text textEmail;


    @FXML private Button buttonConfirmCode;
    @FXML private Label lHintCode;
    @FXML private TextField tFieldCode;
    @FXML private Text textDescCode;


    /**
     * <p> A Pane representing either the {@code recAccFirst} or {@code recAccAlt1} root pane view, 
     * depending on {@link com.example.passwordbank.model.AppUser#hasRecoverInfo() hasRecoverInfo} returning value.
     */
    Pane panePage1;
    
    /**
     * <p> A Pane representing either the {@code recAccQuest} or {@code recAccAlt2} root pane view, 
     * depending on {@link com.example.passwordbank.model.AppUser#hasRecoverInfo() hasRecoverInfo} returning value.
     */
    Pane panePage2;
    StartScreenController startController;


    public void initialize() {
        setActions();
        if (App.user.hasRecoverInfo()) 
            {loadNextPages();}
        else loadAltPages();
    }




    
    private void setActions() {
        buttonBack.setOnMouseClicked(event -> {
            startController.changePage(false, startController.pLogin);
        });
        buttonConfirm.setOnMouseClicked(event -> {
            changePage(panePage1, true);
            setPage1Actions();
        });
        textPresentation.setText(" ");
    }





    private void setPage1Actions() {
        if (App.user.hasRecoverInfo()) 
             page1ActionMain();
        else page1ActionAlt();
    }
    private void page1ActionMain() {
        buttonConfirmEmail.setOnMouseClicked(event -> {

        });
        labelAltEmail.setText("  ");
        labelMainEmail.setText("  ");
        textAltEmail.setText("  ");
        textMainEmail.setText("  ");
    }
    private void page1ActionAlt() {
        buttonNext.setOnMouseClicked(event -> {

        });
        textDescription.setText("Confirm your email below to receive a code to recover your account");
        String email = changeEmail();
        textEmail.setText("Your email: " + email);
    }
    
    
    private String changeEmail() {
        int at = App.user.getMainEmail().indexOf("@");
        String email = App.user.getMainEmail();
        String toReplace = email.substring(3, at);
        String star = "";
        for (int i = 0; i < toReplace.length(); i++) {
            star += "*";
        }
        email = email.replace(toReplace, star);
        
        return email;
    }





    private void setPage2Actions() {

    }


    
    

    private void changePage(Pane pageToGo, boolean isForward) {
        transitionAnimation(pageToGo, isForward);
    }



    private void transitionAnimation(Pane pageToGo, boolean isForward) {
        if (isForward) {
            SlideAnimation slide = new SlideInRight(pageToGo);
            slide.setSpeed(1.5);
            stackPaneMain.getChildren().clear();
            stackPaneMain.getChildren().add(pageToGo);
            slide.play();
        } else {
            SlideAnimation slide = new SlideInLeft(pageToGo);
            slide.setSpeed(1.5);
            stackPaneMain.getChildren().clear();
            stackPaneMain.getChildren().add(pageToGo);
            slide.play();
        }
    }

    
    private void loadNextPages() {
        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("views/recAccFirst.fxml"));
            Parent root1 = loader1.load();
            panePage1 = (Pane) root1;
            
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("views/recAccQuest.fxml"));
            Parent root2 = loader2.load();
            panePage2 = (Pane) root2;
            
        } catch (IOException e) {
            System.out.println("Error: An error happened trying to load next pages");
            System.out.println(e.getMessage());                    e.printStackTrace();
        }
    }

    private void loadAltPages() {
        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("views/recAccAlt1.fxml"));
            loader1.setController(this);
            Parent root1 = loader1.load();
            panePage1 = (Pane) root1;
            
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("views/recAccAlt2.fxml"));
            // loader2.setController(this);
            Parent root2 = loader2.load();
            panePage2 = (Pane) root2;
            
        } catch (IOException e) {
            System.out.println("Error: An error happened trying to load next pages");
            System.out.println(e.getMessage());                    e.printStackTrace();
        }
    }
}