package com.example.passwordbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ModalPasswordController {
    
    @FXML private Button buttonCancel;
    @FXML private Button buttonSave;
    @FXML private Button buttonViewPass;
    @FXML private Label lHIntUsername;
    @FXML private Label lHintPassword;
    @FXML private Label labelPassword;
    @FXML private Label labelUsername;
    @FXML private PasswordField pFPassword;
    @FXML private TextField tFPassword;
    @FXML private TextField tFUsername;
    @FXML private Text textIdentifier;


    public void initialize() {

    }
}
