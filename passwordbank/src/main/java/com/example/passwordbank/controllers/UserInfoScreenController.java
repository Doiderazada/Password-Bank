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
    }


    private void setActions() {
        buttonBack.setOnMouseClicked(event -> {
            App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("settings"));
        });
        buttonLogout.setOnMouseClicked(event -> {
            App.stayLoggedIn = false;
            App.setMinAppSize();
            App.changeScreen("start");
        });
        buttonBack.getStyleClass().setAll("button-prev");
        buttonDeleteAcc.getStyleClass().setAll("button-Delete");
        buttonLogout.getStyleClass().setAll("button-Logout");
    }


}