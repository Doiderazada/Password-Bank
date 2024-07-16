package com.example.passwordbank.controllers;

import com.example.passwordbank.model.Login;
import animatefx.animation.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModalPasswordController {
    
    @FXML private Button buttonCancel;
    @FXML private Button buttonSave;
    @FXML private Button buttonViewPass;
    @FXML private Label lHIntIdentifier;
    @FXML private Label lHIntUsername;
    @FXML private Label lHintPassword;
    @FXML private Label labelIdentifier;
    @FXML private Label labelPassword;
    @FXML private Label labelUsername;
    @FXML private PasswordField pFPassword;
    @FXML private StackPane sPaneBackground;
    @FXML private TextField tFIdentifier;
    @FXML private TextField tFPassword;
    @FXML private TextField tFUsername;


    private static Login login;


    public void initialize() {
        setTexts();
        if (login != null) setFields();
        setActions();
        sPaneBackground.setBackground(Background.fill(Color.TRANSPARENT));
    }


    private void setTexts() {
        labelIdentifier.setText("Login identifier");
        labelUsername.setText("Email/Username");
        labelPassword.setText("Password");
    }


    private void setFields() {
        tFIdentifier.setText(login.getIdentifier());
        tFUsername.setText(login.getUserName());
        pFPassword.setText(login.getPassword().getPass());
    }

    private void setActions() {
        buttonViewPass.getStyleClass().setAll("button-HidePass");
        buttonSave.getStyleClass().setAll("button-SaveChange2");
        buttonCancel.getStyleClass().setAll("button-Cancel");
        buttonCancel.setOnMouseClicked(event -> closeModal());
        buttonSave.setOnMouseClicked(event -> saveChanges());
        buttonViewPass.setOnMousePressed(event -> {
            tFPassword.setText(pFPassword.getText());
            pFPassword.setVisible(false);
            buttonViewPass.getStyleClass().setAll("button-HidePass");
        });
        buttonViewPass.setOnMouseReleased(event -> {
            tFPassword.clear();
            pFPassword.setVisible(true);
            buttonViewPass.getStyleClass().setAll("button-ViewPass");
        });
        tFIdentifier.setOnKeyTyped(event -> {
            tFIdentifier.setBorder(Border.EMPTY);
            lHIntIdentifier.setVisible(false);
        });
        tFUsername.setOnKeyTyped(event -> {
            tFUsername.setBorder(Border.EMPTY);
            lHIntUsername.setVisible(false);
        });
        pFPassword.setOnKeyTyped(event -> {
            pFPassword.setBorder(Border.EMPTY);
            lHintPassword.setVisible(false);
        });

        lHIntIdentifier.setVisible(false);
        lHIntUsername.setVisible(false);
        lHintPassword.setVisible(false);
    }

    private void saveChanges() {
        if (verifyFields()) {
            if (login == null) login = new Login();
            login.setIdentifier(tFIdentifier.getText());
            login.setUserName(tFUsername.getText());
            login.setPassword(pFPassword.getText());
            closeModal();
        }
    }


    private boolean verifyFields() {
        if (tFIdentifier.getText().isBlank()) {
            Shake shake = new Shake(tFIdentifier);
            tFIdentifier.setBorder(Border.stroke(Color.RED));
            lHIntIdentifier.setText("The field cannot be empty");
            lHIntIdentifier.setTextFill(Color.RED);
            lHIntIdentifier.setVisible(true);
            shake.setSpeed(1);
            shake.play();
            return false;
        }
        
        if (tFUsername.getText().isBlank()) {
            Shake shake = new Shake(tFUsername);
            tFUsername.setBorder(Border.stroke(Color.RED));
            lHIntUsername.setText("The field cannot be empty");
            lHIntUsername.setTextFill(Color.RED);
            lHIntUsername.setVisible(true);
            shake.setSpeed(1);
            shake.play();
            return false;
        }
        
        if (pFPassword.getText().isBlank()) {
            Shake shake1 = new Shake(pFPassword);
            Shake shake2 = new Shake(tFPassword);
            Shake shake3 = new Shake(buttonViewPass);
            pFPassword.setBorder(Border.stroke(Color.RED));
            lHintPassword.setText("The field cannot be empty");
            lHintPassword.setTextFill(Color.RED);
            lHintPassword.setVisible(true);
            shake1.setSpeed(1);
            shake1.play();
            shake2.setSpeed(1);
            shake2.play();
            shake3.setSpeed(1);
            shake3.play();
            return false;
        }

        return true;
    }

    public static void setLoginToShow(Login loginToShow) {
        login = loginToShow;
    }

    public static Login getLogin() {
        return login;
    }


    private void closeModal() {
        Stage closeStage = (Stage) buttonCancel.getScene().getWindow();
        closeStage.close();
    }
}
