package com.example.passwordbank;

import java.util.Collections;
import com.example.passwordbank.controllers.BaseController;

import com.example.passwordbank.model.Login;
import com.example.passwordbank.model.AppUser;
import com.example.passwordbank.utilities.FilesManager;
import com.example.passwordbank.utilities.LoginList;
import com.example.passwordbank.utilities.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Password-Bank App class
 */
public class App extends Application {


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
    public  static LoginList logs;

    
    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        setStage(stage);
        configStage();
        setStartAction();
        Scene mainScene;

        if (stayLoggedIn) {
            mainScene = sceneManager.loadPage("base");
            setDefAppSize();
        }
        else {
            mainScene = sceneManager.loadPage("start");
            setMinAppSize();
        }
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    @Override
    public void stop() {
        closeApplication();
    }


    


    private void setStartAction() {
        if (FilesManager.haveUser) {
            haveUser = true;
            user = filesManager.openUserFile();
            user.getPassword().retrievePass();
            darkMode = user.isDarkMode();
            stayLoggedIn = user.isStayLoggedIn();
        }

        if (FilesManager.havePass) {
            logs = filesManager.openLoginsFile();
            Collections.sort(logs);
            for (Login login : logs) {
                login.getPassword().retrievePass();
            }
        }
    }

    private final void closeApplication() {
        if(user != null) {
            System.out.println("Saving files");
            user.getPassword().protectPassword();
            if (logs != null) {
                Collections.sort(logs);
                for (Login login : logs) {
                    login.getPassword().protectPassword();
                }
            }
            user.setDarkMode(darkMode);
            user.setStayLogged(stayLoggedIn);
            filesManager.closeFiles(user, logs);
        }   
        System.out.println("Closing Application...");
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
    


    public static void changePage(String sceneName) {
        primaryStage.setX(primaryStage.getX());
        primaryStage.setY(primaryStage.getY());
        primaryStage.setScene(sceneManager.loadPage(sceneName));
        primaryStage.centerOnScreen();
    }
}