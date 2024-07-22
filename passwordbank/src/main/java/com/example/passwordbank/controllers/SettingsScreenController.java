package com.example.passwordbank.controllers;

import com.example.passwordbank.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SettingsScreenController {

    @FXML private Button buttonAbout;
    @FXML private Button buttonAppearence;
    @FXML private Button buttonUserInfo;
    @FXML private GridPane gPaneSettings;
    @FXML private Label labelAbout;
    @FXML private Label labelAppearence;
    @FXML private Label labelUserInfo;
    @FXML private Text textAbout;
    @FXML private Text textAppearence;
    @FXML private Text textSettings;
    @FXML private Text textTitle;
    @FXML private Text textUserInfo;


    public void initialize() {
        setActions();
        setTexts();
        setTextTheme();
        setButtonsStyle();
    }


    private void setActions() {
        buttonAbout.setOnMouseClicked(event -> changeTo("about"));
        buttonAppearence.setOnMouseClicked(event -> {
            App.darkMode = !App.darkMode;
            App.baseCtrlInstance.setStyle();
            setButtonsStyle();setTextTheme();
        });
        buttonUserInfo.setOnMouseClicked(event -> changeTo("userInfo"));
    }


    private void changeTo(String page) {
        App.baseCtrlInstance.nextPage(App.baseCtrlInstance.loadPane(page));
    }


    private void setButtonsStyle() {
        if (App.darkMode) {buttonAppearence.getStyleClass().setAll("button-DarkMode");}
        else {buttonAppearence.getStyleClass().setAll("button-LightMode");}
        buttonAbout.getStyleClass().setAll("button-Next");
        buttonUserInfo.getStyleClass().setAll("button-Next");
    }


    public void setTexts() {
        labelAbout.setText("Informations about the program such as application guide, version, and related informations");
        labelAppearence.setText("Click the button to switch bettween Dark and Light Mode");
        labelUserInfo.setText("All the informations related to the user, as the application Username and password");
    }


    private void setTextTheme() {
        BaseController.setTextTheme(new Text[] {textAbout, textAppearence, textSettings, textUserInfo},
                                    new Label[] {labelAbout, labelAppearence, labelUserInfo});
    }
}