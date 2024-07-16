package com.example.passwordbank.controllers;

import java.io.IOException;

import com.example.passwordbank.App;
import com.example.passwordbank.utilities.FXWindowControl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML private BorderPane bPaneMain;
    @FXML private Button buttonClose;
    @FXML private Button buttonHome;
    @FXML private Button buttonMaximize;
    @FXML private Button buttonMenu;
    @FXML private Button buttonMinimize;
    @FXML private Button buttonPassword;
    @FXML private Button buttonSettings;
    @FXML private GridPane gPaneMenu;
    @FXML private GridPane gPaneTitleBar;
    @FXML private GridPane gPaneTop;
    @FXML private ScrollPane sPaneMain;
    @FXML private StackPane stackPaneMain;

    private Pane homePane;
    private HomeScreenController homeCtrl;
    private Pane passPane;
    private PasswordScreenController passCtrl;
    private Pane settPane;
    
    private final Text menuText = new Text();
    private final Text homeText = new Text();
    private final Text passText = new Text();
    private final Text settText = new Text();
    
    private boolean menuMaximized = false;
    private final double minXMenu = 50;
    private final double maxXMenu = 230;



    public void initialize() {
        App.baseCtrlInstance = this;
        loadMainPages();
        changePage(homePane);
        setActions();
        setStyle();
        
        new Thread(() -> {
            try {Thread.sleep(200);} 
            catch (InterruptedException e) {e.printStackTrace();}
            new FXWindowControl(buttonMinimize, buttonMaximize, buttonClose);
        }).start();
    }


    


    private void setActions() {
        buttonHome.setOnMouseClicked(event -> {
            homeCtrl.findMostUsedPass();
            homeCtrl.findOldestPass();
            changePage(homePane);
        });
        buttonPassword.setOnMouseClicked(event -> {changePage(passPane);});
        buttonSettings.setOnMouseClicked(event -> {changePage(settPane);});
        
        buttonMenu.setOnMouseClicked(event -> {
            if (menuMaximized) {
                gPaneMenu.setPrefWidth(minXMenu);
                editMenuButtonsSize(menuMaximized);
            } else {
                gPaneMenu.setPrefWidth(maxXMenu);
                editMenuButtonsSize(menuMaximized);
            }
            menuMaximized = !menuMaximized;
        });
    }






    private void editMenuButtonsSize(boolean isMenuMaximized) {
        if (isMenuMaximized) {
            buttonHome.setPrefSize(maxXMenu-10, 40);
            buttonMenu.setPrefSize(maxXMenu-10, 40);
            buttonPassword.setPrefSize(maxXMenu-10, 40);
            buttonSettings.setPrefSize(maxXMenu-10, 40);
            buttonHome.setGraphic(homeText);
            buttonMenu.setGraphic(menuText);
            buttonPassword.setGraphic(passText);
            buttonSettings.setGraphic(settText);
        } else {
            buttonHome.setPrefSize(50, 40);
            buttonMenu.setPrefSize(50, 40);
            buttonPassword.setPrefSize(50, 40);
            buttonSettings.setPrefSize(50, 40);
            buttonHome.setGraphic(null);
            buttonMenu.setGraphic(null);
            buttonPassword.setGraphic(null);
            buttonSettings.setGraphic(null);
        }
    }



    private void createMenuText(){
        menuText.setText("Menu");
        menuText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        menuText.setFill(Color.WHITE);
        homeText.setText("Home");
        homeText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        homeText.setFill(Color.WHITE);
        passText.setText("Passwords");
        passText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        passText.setFill(Color.WHITE);
        settText.setText("Settings");
        settText.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 24));
        settText.setFill(Color.WHITE);
    }

    private void setButtonsStyle(){
        buttonClose.getStyleClass().setAll("button-close");
        buttonMaximize.getStyleClass().setAll("button-maximize");
        buttonMinimize.getStyleClass().setAll("button-minimize");
        buttonHome.getStyleClass().setAll("button-home");
        buttonMenu.getStyleClass().setAll("button-menu");
        buttonPassword.getStyleClass().setAll("button-password");
        buttonSettings.getStyleClass().setAll("button-settings");
    }



    private void loadMainPages() {
        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("views/home.fxml"));
            Parent root1 = loader1.load();
            homeCtrl = loader1.getController();
            homePane = (Pane) root1;
            
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("views/passwords.fxml"));
            Parent root2 = loader2.load();
            passCtrl = loader2.getController();
            passPane = (Pane) root2;
            
            FXMLLoader loader3 = new FXMLLoader(App.class.getResource("views/settings.fxml"));
            Parent root3 = loader3.load();
            settPane = (Pane) root3;
        } catch (IOException e) {System.out.println(e.getMessage());         e.printStackTrace();}
    }

    protected Pane loadPane(String pageName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/" + pageName + ".fxml"));
            Parent root;
            root = loader.load();
            Pane paneToLoad = (Pane) root;
            return paneToLoad;
        } catch (IOException e) {System.out.println(e.getMessage());        e.printStackTrace();}
        return null;
    }

    

    protected void changePage(Pane nextPane) {
        stackPaneMain.getChildren().clear();
        stackPaneMain.getChildren().add(nextPane);
    }



    protected void setStyle() {
        bPaneMain.setBackground(Background.fill(Color.TRANSPARENT));
        if (App.darkMode) 
             gPaneMenu.setBackground(Background.fill(Color.valueOf("#3B3B3B")));
        else gPaneMenu.setBackground(Background.fill(Color.valueOf("#8A8A8A")));
        
        homeCtrl.setTextTheme();
        passCtrl.setTextTheme();
        setButtonsStyle();
        createMenuText();
        setStyle(sPaneMain, stackPaneMain, homePane, passPane, settPane);
    }

    public static void setStyle(Region... panes){
        if (App.darkMode) 
             {for (Region pane : panes) {pane.setBackground(Background.fill(Color.valueOf("#292929")));}} 
        else {for (Region pane : panes) {pane.setBackground(Background.fill(Color.WHITE));}}
    }

    public static void setTextTheme(Text[] texts, Label... labels) {
        if (App.darkMode) {
            for (Label label : labels)  {label.setTextFill(Color.WHITE);}
            for (Text text : texts)     {text.setFill(Color.WHITE);}
        } else {
            for (Label label : labels)  {label.setTextFill(Color.BLACK);}
            for (Text text : texts)     {text.setFill(Color.BLACK);}
        }
    }
}