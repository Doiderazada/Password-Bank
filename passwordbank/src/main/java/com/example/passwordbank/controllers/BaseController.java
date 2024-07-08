package com.example.passwordbank.controllers;

import java.io.IOException;

import com.example.passwordbank.App;
import com.example.passwordbank.utilities.FXWindowControl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BaseController {

    @FXML public Button buttonClose;
    @FXML public Button buttonMaximize;
    @FXML public Button buttonMinimize;

    @FXML private Button buttonHome;
    @FXML private Button buttonMenu;
    @FXML private Button buttonPassword;
    @FXML private Button buttonSettings;
    @FXML private BorderPane bPaneMain;
    @FXML private GridPane gPaneMenu;
    @FXML private GridPane gPaneWindowTop;
    @FXML private ScrollPane sPaneMain;
    @FXML private StackPane stackPaneMain;

    private Pane homePane;
    private Pane passPane;
    private Pane settPane;
    
    private final Text menuText = new Text();
    private final Text homeText = new Text();
    private final Text passText = new Text();
    private final Text settText = new Text();
    
    private final double minXMenu = 50;
    private final double maxXMenu = 230;
    private boolean menuMaximized = false;



    @FXML 
    public void initialize() {
        App.baseCtrlInstance = this;
        loadMainPages();
        changePage(homePane);
        setActions();
        // setStyle();
        
        new Thread(() -> {
            try {Thread.sleep(200);} 
            catch (InterruptedException e) {e.printStackTrace();}
            new FXWindowControl(buttonMinimize, buttonMaximize, buttonClose);
        }).start();
    }


    


    private void setActions() {
        buttonHome.setOnMouseClicked(event -> {changePage(homePane);});
        buttonPassword.setOnMouseClicked(event -> {changePage(passPane);});
        buttonSettings.setOnMouseClicked(event -> {changePage(settPane);});
        
        buttonMenu.setOnMouseClicked(event -> {
            if (menuMaximized) {
                gPaneMenu.setPrefWidth(minXMenu);
                menuMaximized = false;
                editMenuButtonsSize(menuMaximized);
            } else {
                gPaneMenu.setPrefWidth(maxXMenu);
                menuMaximized = true;
                editMenuButtonsSize(menuMaximized);
            }
        });
    }






    private void editMenuButtonsSize(boolean isMenuMaximized) {
        if (isMenuMaximized) {
            buttonMenu.setPrefSize(maxXMenu-10, 40);
            buttonHome.setPrefSize(maxXMenu-10, 40);
            buttonPassword.setPrefSize(maxXMenu-10, 40);
            buttonSettings.setPrefSize(maxXMenu-10, 40);
            
            buttonHome.setGraphic(homeText);
            buttonMenu.setGraphic(menuText);
            buttonPassword.setGraphic(passText);
            buttonSettings.setGraphic(settText);
        } else {
            buttonMenu.setPrefSize(50, 40);
            buttonHome.setPrefSize(50, 40);
            buttonPassword.setPrefSize(50, 40);
            buttonSettings.setPrefSize(50, 40);

            buttonHome.setGraphic(null);
            buttonMenu.setGraphic(null);
            buttonPassword.setGraphic(null);
            buttonSettings.setGraphic(null);
        }
    }

    private void createMenuText(){
        menuText.setStroke(Color.BLACK);
        menuText.setText("Menu");
        menuText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        menuText.setFill(Color.WHITE);
        menuText.setStrokeWidth(.8);

        homeText.setStroke(Color.BLACK);
        homeText.setText("Home");
        homeText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        homeText.setFill(Color.WHITE);
        homeText.setStrokeWidth(.8);       
        
        passText.setStroke(Color.BLACK);
        passText.setText("Passwords");
        passText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        passText.setFill(Color.WHITE);
        passText.setStrokeWidth(.8);       
        
        settText.setStroke(Color.BLACK);
        settText.setText("Settings");
        settText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        settText.setFill(Color.WHITE);
        settText.setStrokeWidth(.8);       
    }




    private void setButtonsStyle(){
        buttonHome.getStyleClass().setAll("button-home");
        buttonMenu.getStyleClass().setAll("button-menu");
        buttonPassword.getStyleClass().setAll("button-password");
        buttonSettings.getStyleClass().setAll("button-settings");
    }



    private void loadMainPages() {
        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("views/home.fxml"));
            Parent root1 = loader1.load();
            homePane = (Pane) root1;
            
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("views/passwords.fxml"));
            Parent root2 = loader2.load();
            passPane = (Pane) root2;
            
            FXMLLoader loader3 = new FXMLLoader(App.class.getResource("views/settings.fxml"));
            Parent root3 = loader3.load();
            settPane = (Pane) root3;
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    protected Pane loadPane(String pageName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/" + pageName + ".fxml"));
            Parent root;
            root = loader.load();
            Pane paneToLoad = (Pane) root;
            return paneToLoad;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    protected void changePage(Pane nextPane) {
        stackPaneMain.getChildren().clear();
        stackPaneMain.getChildren().add(nextPane);
    }



    private void setStyle() {
        bPaneMain.setBackground(Background.EMPTY);
        if (App.darkMode) 
             gPaneMenu.setBackground(Background.fill(Color.valueOf("#3B3B3B")));
        else gPaneMenu.setBackground(Background.fill(Color.valueOf("#8A8A8A")));
        

        setButtonsStyle();
        createMenuText();
        setStyle(sPaneMain, stackPaneMain);
    }

    public static void setStyle(Region... panes){
        if (App.darkMode) {
            for (Region pane : panes) {
                pane.setBackground(Background.fill(Color.valueOf("#292929")));
            }
        } else {
            for (Region pane : panes) {
                pane.setBackground(Background.fill(Color.WHITE));
            }
        }
    }
}