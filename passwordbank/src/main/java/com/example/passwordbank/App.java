package com.example.passwordbank;

import com.example.passwordbank.controllers.BaseController;
import com.example.passwordbank.model.Login;
import com.example.passwordbank.model.AppUser;
import com.example.passwordbank.utilities.FilesManager;
import com.example.passwordbank.utilities.SceneManager;
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
    private static SceneManager sceneManager = new SceneManager();
    private static FilesManager filesManager = new FilesManager();
    public  static BaseController baseCtrlInstance;
    public  static final double defH = 724;
    public  static final double defW = 1284;
    public  static final double minH = 600;
    public  static final double minW = 400;
    public  static boolean haveUser;
    public  static boolean darkMode = true;
    public  static boolean stayLoggedIn = false;
    public  static AppUser user;
    public  static Login[] logs;

    
    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) {
        setStage(stage);
        configStage();
        setStartAction();
        setCloseAction();

        mainScene = sceneManager.loadScreen("start");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    


    private void setStartAction() {
        if (FilesManager.haveUser) {
            haveUser = true;
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
            filesManager.closeFiles(user, logs);
        });   
    }



    private void setStage(Stage stage) {
        primaryStage = stage;
    }
    public static Stage getStage() {
        return primaryStage;
    }
    public static Scene getScene() {
        return primaryStage.getScene();
    }



    private void configStage() {
        primaryStage.setTitle("Password Bank");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(true);
        primaryStage.centerOnScreen();
    }



    public static void setDefAppSize() {
        primaryStage.setMinHeight(defH);
        primaryStage.setMinWidth(defW);
        primaryStage.setHeight(defH);
        primaryStage.setWidth(defW);
        primaryStage.centerOnScreen();
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
        primaryStage.setScene(sceneManager.loadScreen(sceneName));
        primaryStage.centerOnScreen();
    }

}