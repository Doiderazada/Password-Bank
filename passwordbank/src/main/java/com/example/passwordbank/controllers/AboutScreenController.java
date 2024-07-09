package com.example.passwordbank.controllers;

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
        buttonPDF.setText("Open user guide");
        buttonPDF.getStyleClass().setAll("button-PDF-black");
    }

}