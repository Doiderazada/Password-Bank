package com.example.passwordbank;


import com.example.passwordbank.model.Login;
import com.example.passwordbank.model.AppUser;
import com.example.passwordbank.utilities.FilesManager;
import com.example.passwordbank.utilities.SceneManager;

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
    private static SceneManager sceneManager = new SceneManager();
    private static FilesManager filesManager = new FilesManager();
    public  static AppUser user;
    public  static Login[] logs;
    public  static boolean darkMode = false;
    public  static final double minH = 724;
    public  static final double minW = 1284;

    
    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) {
        setStage(stage);
        configStage(primaryStage);
        setStartAction();
        setCloseAction();
        mainScene = sceneManager.loadScreen("homeScreen");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    


    private void setStartAction() {
        if (FilesManager.haveUser) {
            user = filesManager.openUserFile();
            user.getPassword().retrievePass();
            darkMode = user.isDarkMode();
        }

        if (FilesManager.havePass) {
            logs = filesManager.openLoginsFile();
            for (Login login : logs) {
                login.getPassword().retrievePass();
            }
        }
    }

    private final void setCloseAction() {
        primaryStage.setOnCloseRequest(event -> {
            user.setDarkMode(darkMode);
            filesManager.savePassFile();
            filesManager.saveUserFile();
        });   
    }


    private void setStage(Stage stage) {
        primaryStage = stage;
    }
    public static Stage getStage() {
        return primaryStage;
    }
    public static Scene getScene() {
        return mainScene;
    }

    private void configStage(Stage primaryStage) {
        primaryStage.setTitle("Password Bank");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(true);
        primaryStage.setHeight(minH);
        primaryStage.setWidth(minW);
        primaryStage.setMinHeight(minH);
        primaryStage.setMinWidth(minW);
        primaryStage.centerOnScreen();
    }


    public static void changeScreen(String sceneName) {
        primaryStage.setX(primaryStage.getScene().getX());
        primaryStage.setY(primaryStage.getScene().getY());
        primaryStage.setScene(sceneManager.loadScreen(sceneName));
    }

}