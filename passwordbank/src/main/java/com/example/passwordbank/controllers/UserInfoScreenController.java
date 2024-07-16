package com.example.passwordbank.controllers;

import com.example.passwordbank.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class UserInfoScreenController {

    @FXML private Button buttonBack;
    @FXML private Button buttonDeleteAcc;
    @FXML private Button buttonLogout;
    @FXML private Button buttonSaveChanges;
    @FXML private Button buttonShowPass;
    @FXML private ChoiceBox<String> cBoxQ1;
    @FXML private ChoiceBox<String> cBoxQ2;
    @FXML private ChoiceBox<String> cBoxQ3;
    @FXML private GridPane gPaneUserInfo;
    @FXML private Label labelA1;
    @FXML private Label labelA2;
    @FXML private Label labelA3;
    @FXML private Label labelAltEmail;
    @FXML private Label labelHintPass;
    @FXML private Label labelHintUsername;
    @FXML private Label labelMainEmail;
    @FXML private Label labelPassword;
    @FXML private Label labelPhoneNum;
    @FXML private Label labelQ1;
    @FXML private Label labelQ2;
    @FXML private Label labelQ3;
    @FXML private Label labelUsername;
    @FXML private PasswordField pFieldPassword;
    @FXML private TextArea tAreaA1;
    @FXML private TextArea tAreaA2;
    @FXML private TextArea tAreaA3;
    @FXML private TextField tFieldAltEmail;
    @FXML private TextField tFieldMainEmail;
    @FXML private TextField tFieldPassword;
    @FXML private TextField tFieldPhoneNum;
    @FXML private TextField tFieldusername;
    @FXML private Text textAccountInfo;
    @FXML private Text textQuestions;
    @FXML private Text textRecovery;
    @FXML private Text textUserInfo;



    public void initialize() {
        setActions();
        setTexts();
        setTextTheme();
        setButtonsStyle();
    }


    
    
    private void setActions() {
        buttonBack.setOnMouseClicked(event -> {
            App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("settings"));
        });
        buttonLogout.setOnMouseClicked(event -> {
            App.stayLoggedIn = false;
            App.getStage().hide();
            App.setMinAppSize();
            App.changePage("start");
            App.getStage().show();
        });
    }
    
    private void setButtonsStyle() {
        buttonBack.getStyleClass().setAll("button-Prev");
        buttonDeleteAcc.getStyleClass().setAll("button-Delete");
        buttonLogout.getStyleClass().setAll("button-Logout");
        buttonSaveChanges.getStyleClass().setAll("button-SaveChange");
        buttonShowPass.getStyleClass().setAll("button-HidePass");
    }
    
    private void setTexts() {
        buttonLogout.setText("Log out");
        buttonDeleteAcc.setText("Delete account");
        buttonSaveChanges.setText("Save changes");
        labelA1.setText("Answer 1");
        labelA2.setText("Answer 2");
        labelA3.setText("Answer 3");
        labelAltEmail.setText("Alternative email");
        labelHintPass.setVisible(false);
        labelHintUsername.setVisible(false);
        labelMainEmail.setText("Main email");
        labelPassword.setText("Password");
        labelPhoneNum.setText("Phone number");
        labelQ1.setText("Question 1");
        labelQ2.setText("Question 2");
        labelQ3.setText("Question 3");
    }

    protected void setTextTheme() {
        BaseController.setTextTheme(new Text[] {textAccountInfo, textQuestions, textRecovery, textUserInfo}, 
                                    new Label[] {labelA1, labelA2, labelA3, labelAltEmail, labelHintPass, labelHintUsername, 
                                                 labelMainEmail, labelPassword, labelPhoneNum, labelQ1, labelQ2, labelQ3});
    }
}