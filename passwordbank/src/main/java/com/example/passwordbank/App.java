package com.example.passwordbank;

import com.example.passwordbank.controllers.BaseController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Password-Bank App class
 */
public class App extends Application {


    private static Scene mainScene;
    private static Stage primaryStage;
    private static ScreenManager screenManager = new ScreenManager();
    public static boolean darkMode = false;
    public static boolean stayLoggedIn = false;
    final public static double minH = 600;
    final public static double minW = 400;
    final public static double defH = 724;
    final public static double defW = 1284;
    public static BaseController baseCtrlInstance;
    
    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        // primaryStage.setTitle("Password Bank");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(true);
        
        
        mainScene = screenManager.loadScreen("start");
        
        primaryStage.setScene(mainScene);
        primaryStage.getScene().setFill(Color.rgb(0, 0, 0, 0.01));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


    public static void setDefAppSize() {
        primaryStage.setMinHeight(defH);
        primaryStage.setMinWidth(defW);
        primaryStage.setHeight(defH);
        primaryStage.setWidth(defW);
    }
    public static void setMinAppSize() {
        primaryStage.setMinHeight(minH);
        primaryStage.setMinWidth(minW);
        primaryStage.setHeight(minH);
        primaryStage.setWidth(minW);
    }
    
    public static void changeScreen(String sceneName) {
        primaryStage.setX(primaryStage.getX());
        primaryStage.setY(primaryStage.getY());
        primaryStage.setScene(screenManager.loadScreen(sceneName));
        primaryStage.getScene().setFill(Color.rgb(0, 0, 0, 0.01));
        primaryStage.centerOnScreen();
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return mainScene;
    }
}