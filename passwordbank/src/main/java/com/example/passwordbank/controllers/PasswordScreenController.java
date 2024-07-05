package com.example.passwordbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PasswordScreenController {

    @FXML private GridPane gPanePassword;

    @FXML private TextField tFSearchPass;

    @FXML private Text textRegPass;


    public void initialize() {
        setActions();
    }

    private void setActions() {
        // tFSearchPass.getStyleClass().setAll("searchBar");
    }
}
