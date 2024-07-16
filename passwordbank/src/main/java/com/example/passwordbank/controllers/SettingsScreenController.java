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
        buttonAbout.setOnMouseClicked(event -> {
            App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("about"));
        });
        buttonAppearence.setOnMouseClicked(event -> {
            if (App.darkMode) {
                App.darkMode = false;
                buttonAppearence.getStyleClass().setAll("button-LightMode");
                App.baseCtrlInstance.setStyle();
                setTextTheme();
            } else {
                App.darkMode = true;
                buttonAppearence.getStyleClass().setAll("button-DarkMode");
                App.baseCtrlInstance.setStyle();
                setTextTheme();
            }
        });
        buttonUserInfo.setOnMouseClicked(event -> {
            App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("userInfo"));
        });

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