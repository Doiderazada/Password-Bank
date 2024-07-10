package com.example.passwordbank.controllers;

import com.example.passwordbank.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AboutScreenController {

    @FXML private Button buttonBack;
    @FXML private Button buttonGitHub;
    @FXML private Button buttonPDF;
    @FXML private Text textAbout;
    @FXML private Text textAboutDescription;
    @FXML private TextFlow textDev;
    @FXML private Text textGuideDescription;
    @FXML private Text textProjectGitHub;
    @FXML private Text textUserGuide;


    public void initialize() {
        setActions();
        buttonPDF.setText("Open user guide");
    }
    
    
    private void setActions() {
        if (App.darkMode) {
            buttonPDF.getStyleClass().setAll("button-PDF-white");
        } else {
            buttonPDF.getStyleClass().setAll("button-PDF-black");
        }
        buttonBack.getStyleClass().setAll("button-Prev");
        buttonBack.setOnMouseClicked(event -> App.baseCtrlInstance.changePage(App.baseCtrlInstance.loadPane("settings")));
    }
}