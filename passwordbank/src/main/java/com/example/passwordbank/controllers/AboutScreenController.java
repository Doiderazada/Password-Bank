package com.example.passwordbank.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

import com.example.passwordbank.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AboutScreenController {

    @FXML private Button buttonBack;
    @FXML private Button buttonGitHub;
    @FXML private Button buttonPDF;
    @FXML private GridPane gPaneAbout;
    @FXML private Label labelDev;
    @FXML private Text textAbout;
    @FXML private Text textAboutDescription;
    @FXML private Text textDev;
    @FXML private Text textGuideDescription;
    @FXML private Text textProjectGitHub;
    @FXML private Text textUserGuide;


    public void initialize() {
        setActions();
        setButtonsStyle();
        setTextTheme();
        setText();
        buttonPDF.setText("Open user guide");
        BaseController.setStyle(gPaneAbout);

        gPaneAbout.widthProperty().addListener((a, b, c) -> {
            textAboutDescription.setWrappingWidth(c.doubleValue()-580);
            textGuideDescription.setWrappingWidth(c.doubleValue()-580);
        });
    }
    
    
    private void setActions() {
        buttonBack.setOnMouseClicked(event -> App.baseCtrlInstance.prevPage());
        buttonGitHub.setOnMouseClicked(event -> {
            try{
                String url = "https://github.com/Doiderazada/Password-Bank";
                Desktop desktop = Desktop.getDesktop();
                if (Desktop.isDesktopSupported()) {
                    desktop.browse(new URI(url));
                }
            } catch(IOException | URISyntaxException e) {
                System.out.println("There was an error trying to follow the link" + e.getMessage());
                e.printStackTrace();
            }
        });
        Tooltip gitTip = new Tooltip("Github projet page");
        gitTip.setShowDelay(Duration.millis(150));
        gitTip.setShowDuration(Duration.millis(800));
        gitTip.setAutoHide(true);
        buttonGitHub.setTooltip(gitTip);
        buttonPDF.setOnMouseClicked(null);
    }

    private void setButtonsStyle() {
        if (App.darkMode) {
            buttonPDF.getStyleClass().setAll("button-PDF-white");
            buttonGitHub.getStyleClass().setAll("button-GitHub-white");
        } else {
            buttonPDF.getStyleClass().setAll("button-PDF-black");
            buttonGitHub.getStyleClass().setAll("button-GitHub-black");
        }
        buttonBack.getStyleClass().setAll("button-Prev");
    }

    private void setTextTheme() {
        if (App.darkMode) buttonPDF.setTextFill(Color.WHITE);
        else buttonPDF.setTextFill(Color.BLACK);
        BaseController.setTextTheme(new Text[] {textAbout, textAboutDescription, textGuideDescription,
                                                textProjectGitHub, textUserGuide, textDev},
                                    new Label[] {labelDev});
    }


    private void setText() {
        String description = "This program is part of a public, personal project developed by Doidera, student of the Computer Science course at Universidade Federal Rural do Semi-Árido \n\n" +
                             "This program was developed using Java 17 and the graphical library JavaFX version 21 \n\n" +
                             "Although the program needs access to your internet conection for some funcionalities, "  + 
                             "all passwords created and stored in the program are saved locally and thus, cannot be accessed by any other user or the developers itself";

        String userGuide = "For a complete guide on how to use the application on its full, read the User Guide Document bellow";
        String gitText = "Check out the project page";

        textAboutDescription.setText(description);
        textGuideDescription.setText(userGuide);
        textProjectGitHub.setText(gitText);
    }
}