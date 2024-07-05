package com.example.passwordbank;

import com.example.passwordbank.controllers.BaseController;
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
    public static boolean darkMode = true;
    public static boolean stayLoggedIn = false;
    final public static double defH = 724;
    final public static double defW = 1284;
    public  static final double minH = 600;
    public  static final double minW = 400;
    public static BaseController baseCtrlInstance;
    private static SceneManager sceneManager = new SceneManager();
    private static FilesManager filesManager = new FilesManager();
    public  static AppUser user;
    public  static Login[] logs;

    
    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) {
        setStage(stage);
        configStage(primaryStage);
        setStartAction();
        setCloseAction();
        mainScene = sceneManager.loadScreen("start");
        
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
    }

}