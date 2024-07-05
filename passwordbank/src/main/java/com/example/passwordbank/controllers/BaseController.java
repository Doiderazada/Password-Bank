package com.example.passwordbank.controllers;

import java.io.IOException;

import com.example.passwordbank.App;
import com.example.passwordbank.utilities.FXWindowControl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private final double mouseDragOffset = 25;
    private final Stage primaryStage = App.getStage();
    
    private double moveOffSetX, moveOffSetY, mousePosition;
    private boolean isDraggable;



    @FXML 
    public void initialize() {
        App.baseCtrlInstance = this;
        loadMainPages();
        changePage(homePane);
        setActions();
        setStyle();
        primaryStage.setOnShown(event -> {
            new FXWindowControl(bPaneMain, buttonMinimize, buttonMaximize, buttonClose);
        });
    }


    


    private void setActions() {
        buttonHome.setOnMouseClicked(event -> {changePage(homePane);});
        buttonPassword.setOnMouseClicked(event -> {changePage(passPane);});
        buttonSettings.setOnMouseClicked(event -> {changePage(settPane);});
        gPaneWindowTop.setOnMouseClicked(toggleMaxMin());
        
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


        // gPaneWindowTop.setOnMouseEntered(event -> {isDraggable = true;});
        // gPaneWindowTop.setOnMouseExited (event -> {isDraggable = false;});
        gPaneWindowTop.setOnMouseMoved(draggingControl());
        gPaneWindowTop.setOnMousePressed(pressingControl());
        gPaneWindowTop.setOnMouseDragged(doDragging());
    }


    




    protected void changePage(Pane nextPane) {
        stackPaneMain.getChildren().clear();
        stackPaneMain.getChildren().add(nextPane);
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





    private EventHandler<MouseEvent> toggleMaxMin() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if ((arg0.getClickCount() == 2) && 
                    (arg0.getButton() == MouseButton.PRIMARY)) {
                    if (primaryStage.isMaximized()) {
                        primaryStage.setHeight(App.minH);
                        primaryStage.setWidth(App.minW);
                        primaryStage.setMaximized(false);
                        primaryStage.centerOnScreen();
                    } else primaryStage.setMaximized(true);
                } 
            }
        }; 
    }

    private EventHandler<MouseEvent> pressingControl() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                moveOffSetX = primaryStage.getX() - arg0.getScreenX();
                moveOffSetY = primaryStage.getY() - arg0.getScreenY();
            }
        };
    }

    private EventHandler<MouseEvent> draggingControl() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                isDraggable =  false;
                gPaneWindowTop.setCursor(Cursor.DEFAULT);
                mousePosition = arg0.getY();

                if (mousePosition <= mouseDragOffset) {
                    isDraggable = true;
                } else isDraggable = false;
            }
        };
    }
    private EventHandler<MouseEvent> doDragging() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (isDraggable && (arg0.getButton() == MouseButton.PRIMARY)) {
                    primaryStage.setX(arg0.getScreenX() + moveOffSetX);
                    primaryStage.setY(arg0.getScreenY() + moveOffSetY);
                }
            }
        };
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



    public void setStyle(){}
}