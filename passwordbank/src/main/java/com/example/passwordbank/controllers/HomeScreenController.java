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


    ColumnConstraints cons = new ColumnConstraints(350, 350, 350);


    @FXML
    public void initialize() {
        setTexts();
        setTextTheme();
        setUpGridPanes();
        if (App.logs != null) {
            findMostUsedPass();
            findOldestPass();
        }
    }
    
    
    


    private void setUpGridPanes() {
        gPaneLastUsed.getColumnConstraints().add(cons);
        gPaneLastUsed.setVgap(60);
        gPaneOldstReg.getColumnConstraints().add(cons);
        gPaneOldstReg.setVgap(60);;
    }




    private void setTexts() {
        textLastUsed.setText("Last used passwords");
        textOldestRegistered.setText("Last registered passwords");
    }

    protected void setTextTheme() {
        BaseController.setTextTheme(new Text[] {textLastUsed, textOldestRegistered});
    }

    
    protected void findMostUsedPass() {
        if (App.logs != null) {
            LoginList logins = App.logs;
            LoginList updatedList = LoginList.getMostUsed(logins);
            int colCount = 0;
            
            gPaneLastUsed.getChildren().clear();
            for (Login login : updatedList) {
                PasswordFXElement pass = new PasswordFXElement(login);
                gPaneLastUsed.add(pass.getRoot(), colCount++, 0);;
            }
        }
    }
    
    protected void findOldestPass() {
        if (App.logs != null) {
            LoginList logins = App.logs;
            LoginList updatedList = LoginList.getOldestEdited(logins);
            int colCount = 0;
    
            gPaneOldstReg.getChildren().clear();
            for (Login login : updatedList) {
                PasswordFXElement pass = new PasswordFXElement(login);
                gPaneOldstReg.add(pass.getRoot(), colCount++, 0);
            }
        }
    }
}