package com.example.passwordbank;

import javafx.application.Application;
import javafx.scene.Scene;
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
    final public static double minH = 724;
    final public static double minW = 1284;
    
    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        // primaryStage.setTitle("Password Bank");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(true);
        primaryStage.setHeight(minH);
        primaryStage.setWidth(minW);
        primaryStage.setMinHeight(minH);
        primaryStage.setMinWidth(minW);
        
        
        mainScene = screenManager.loadScreen("startScreen");
        
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void changeScreen(String sceneName) {
        primaryStage.setX(primaryStage.getScene().getX());
        primaryStage.setY(primaryStage.getScene().getY());
        primaryStage.setScene(screenManager.loadScreen(sceneName));
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return mainScene;
    }
}