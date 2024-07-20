package com.example.passwordbank.controllers;

import com.example.passwordbank.App;
import com.example.passwordbank.model.Login;
import com.example.passwordbank.utilities.LoginList;
import com.example.passwordbank.utilities.PasswordFXElement;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HomeScreenController {
    

    @FXML private GridPane gPaneHome;
    @FXML private GridPane gPaneLastUsed;
    @FXML private GridPane gPaneOldstReg;
    @FXML private Text textLastUsed;
    @FXML private Text textOldestRegistered;  


    ColumnConstraints cons = new ColumnConstraints(50, 350, 350);


    @FXML
    public void initialize() {
        setTexts();
        setTextTheme();
        setUpGridPanes();
        loadGridPanes();
    }
    
    
    





    private void setUpGridPanes() {
        gPaneLastUsed.getColumnConstraints().add(cons);
        gPaneLastUsed.setHgap(50);
        gPaneOldstReg.getColumnConstraints().add(cons);
        gPaneOldstReg.setHgap(50);
    }




    private void setTexts() {
        textLastUsed.setText("Most used passwords");
        textOldestRegistered.setText("Oldest registered passwords");
    }

    protected void setTextTheme() {
        BaseController.setTextTheme(new Text[] {textLastUsed, textOldestRegistered});
    }

    
    protected void findMostUsedPass() {
        gPaneLastUsed.getChildren().clear();
        LoginList logins = App.logs;
        LoginList updatedList = LoginList.getMostUsed(logins);
        int colCount = 0;
        
        gPaneLastUsed.getChildren().clear();
        for (Login login : updatedList) {
            PasswordFXElement pass = new PasswordFXElement(login);
            gPaneLastUsed.add(pass.getRoot(), colCount++, 0);;
        }
    }
    
    protected void findOldestPass() {
        gPaneOldstReg.getChildren().clear();
        LoginList logins = App.logs;
        LoginList updatedList = LoginList.getOldestEdited(logins);
        int colCount = 0;

        gPaneOldstReg.getChildren().clear();
        for (Login login : updatedList) {
            PasswordFXElement pass = new PasswordFXElement(login);
            gPaneOldstReg.add(pass.getRoot(), colCount++, 0);
        }
        
    }


    protected void loadGridPanes() {
        if ((App.logs != null) && (App.logs.size() >= 3)) {
            findMostUsedPass();
            findOldestPass();
            PasswordFXElement.setStokeByTheme();
        }
    }
}