package com.example.passwordbank.controllers;

import java.util.Map;

import com.example.passwordbank.App;
import com.example.passwordbank.model.Login;
import com.example.passwordbank.utilities.LoginList;
import com.example.passwordbank.utilities.ModalManager;
import com.example.passwordbank.utilities.ModalState;
import com.example.passwordbank.utilities.PasswordFXElement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PasswordScreenController {

    @FXML private Button buttonAddPass;
    @FXML private GridPane gPanePassword;
    @FXML private GridPane gPanePassReg;
    @FXML private TextField tFSearchPass;
    @FXML private Text textRegPass;

    private LoginList logins = App.logs;
    Map<Object, Object> gridPaneProperties;
    int colsMin = 3, colsMax = 4, limCount = 3;
    int minW = 350, minH = 300;
    ColumnConstraints cons = new ColumnConstraints(350, 350, 350);

    public void initialize() {
        setActions();
        setTexts();
        setTextTheme();
        setButtonsStyle();
        setUpGridPanes();

        if (logins != null) printLogins();
        

        App.getStage().maximizedProperty().addListener((a, b, c) -> {
            if (c) {limCount = colsMax;
            gPanePassReg.setHgap(100);}
            else {limCount = colsMin;
            gPanePassReg.setHgap(60);}
            printLogins();
        });
    }

    
    
    
    private void setUpGridPane() {
        gPanePassReg.getColumnConstraints().add(cons);
        gPanePassReg.setHgap(60);
        gPanePassReg.setVgap(80);
    }




    private void printLogins() {
        int rowCount = 0, colCount = 0;
        gPanePassReg.getChildren().clear();
        for (Login login : logins) {
            PasswordFXElement pass = new PasswordFXElement(login);
            gPanePassReg.add(pass.getRoot(), colCount++, rowCount);
            if (colCount == limCount) {
                colCount = 0;
                rowCount++;
                gPanePassReg.addRow(rowCount);
            }
        }
    }




    private void setActions() {
        buttonAddPass.setOnMouseClicked(event -> createNewPass());
        tFSearchPass.setOnKeyTyped(event -> {
            if (tFSearchPass.getText().length() >= 3) {searchPass();}
            logins = App.logs;
            if (tFSearchPass.getText().isEmpty()) {printLogins();}
        });
    }
    
    




    private void createNewPass() {
        if (App.logs == null) {
            logins = new LoginList();
            App.logs = logins;
        }
        ModalManager modal = new ModalManager(null, buttonAddPass, ModalState.CREATE);
        modal.showModal();
        Login newLogin = new Login();
        newLogin = modal.getLoginUpdated();
        logins.add(newLogin);
        printLogins();
        modal = null;
        newLogin = null;
    }

    private void searchPass() {
        String searchedPass = tFSearchPass.getText();
        searchedPass = searchedPass.toLowerCase();
        java.util.Iterator<Login> it = logins.iterator();
        LoginList foundList = new LoginList();
        Login foundLog = new Login();

        while (it.hasNext()) {
            foundLog = it.next();
            if (foundLog.getIdentifier().toLowerCase().startsWith(searchedPass)) {
                foundList.add(foundLog);
            }
        }
        logins = foundList;
        foundList = null;
        printLogins();
    }





    private void setButtonsStyle() {
        buttonAddPass.getStyleClass().setAll("button-AddPass");
    }
    
    private void setTexts() {
        textRegPass.setText("Passwords registered");
    }

    protected void setTextTheme() {
        BaseController.setTextTheme(new Text[] {textRegPass});
    }
}
