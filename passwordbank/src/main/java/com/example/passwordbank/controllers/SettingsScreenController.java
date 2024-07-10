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
        setText();
        setButtonsStyle();
    }






    private void setActions() {
        buttonAbout.setOnMouseClicked(event -> {
            App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("about"));
        });
        buttonAppearence.setOnMouseClicked(event -> {
            if (App.darkMode) {
                App.darkMode = false;
            } else {
                App.darkMode = true;
            }
        });
        buttonUserInfo.setOnMouseClicked(event -> {
            App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("userInfo"));
        });

    }


    public void setText() {
        labelAbout.setText("Informations about the program such as application guide, version, and related informations");
        labelAppearence.setText("Click the button to switch bettween Dark and Light Mode");
        labelUserInfo.setText("All the informations related to the user, as the application Username and password");
    }


    private void setButtonsStyle() {
        buttonAbout.getStyleClass().setAll("button-Next");
        buttonUserInfo.getStyleClass().setAll("button-Next");
    }

}
