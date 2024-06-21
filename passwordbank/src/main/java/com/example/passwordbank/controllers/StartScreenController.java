package com.example.passwordbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class StartScreenController extends BaseController{
    

    @FXML private HBox hBoxLastUsed;
    @FXML private HBox hBoxOldestRegistered;
    @FXML private Label labelLastUsedPassword;
    @FXML private Label labelLastUsedUser;
    @FXML private Label labelOldRegPassword;
    @FXML private Label labelOldRegUser;
    @FXML private Pane paneStartScreen;
    @FXML private PasswordField pfLastUsedPassword;
    @FXML private PasswordField pfOldRegPassword;
    @FXML private StackPane sPaneLastUsedPassInfo;
    @FXML private StackPane sPaneOldRegPassInfo;
    @FXML private ScrollPane sPaneStartScreen;
    @FXML private Text textLastUsed;
    @FXML private Text textLastUsedPassIdentfier;
    @FXML private Text textOldRegPassInfo;
    @FXML private Text textOldestRegistered;
    @FXML private Text textTitle;
    @FXML private TextField tfLastUsedUser;
    @FXML private TextField tfOldRegUser;


    @FXML
    public void initialize() {
        super.initialize();
        interActions();
    }
    
    
    private void interActions() {
        
    }



    


    
}
