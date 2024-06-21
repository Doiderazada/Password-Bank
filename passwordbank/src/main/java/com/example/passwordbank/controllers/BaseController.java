package com.example.passwordbank.controllers;

import com.example.passwordbank.App;
import com.example.passwordbank.WindowControlHelper;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BaseController {

    @FXML private Button buttonClose;
    @FXML private Button buttonHome;
    @FXML private Button buttonMaximize;
    @FXML private Button buttonMenu;
    @FXML private Button buttonMinimize;
    @FXML private Button buttonPassword;
    @FXML private Button buttonSettings;
    @FXML private BorderPane bPaneMain;
    @FXML private GridPane gPaneMenu;
    @FXML private GridPane gPaneWindowTop;
    @FXML private ScrollPane sPaneMain;
    

    private final double minXMenu = 100;
    private final double maxXMenu = 270;
    private boolean menuMaximized = false;
    private final double mouseDragOffset = 25;
    private final Stage primaryStage = App.getStage();
    
    private double moveOffSetX, moveOffSetY, mousePosition;
    private boolean isDraggable;



    @FXML 
    public void initialize() {
        setActions();
        setStyle();
        primaryStage.setOnShown(event -> {
            new WindowControlHelper(bPaneMain, buttonMinimize, buttonMaximize, buttonClose);
        });
    }


    


    private void setActions() {
        buttonHome.setOnMouseClicked(event -> {App.changeScreen("");} );
        buttonMenu.setOnMouseClicked(event -> {
            if (menuMaximized) {
                gPaneMenu.setPrefWidth(minXMenu);
                menuMaximized = false;
            } else {
                gPaneMenu.setPrefWidth(maxXMenu);
                menuMaximized = true;
            }
        });
        buttonPassword.setOnMouseClicked(event -> {App.changeScreen("");} );
        buttonSettings.setOnMouseClicked(event -> {App.changeScreen("");} );
        gPaneWindowTop.setOnMouseClicked(toggleMaxMin());
        // gPaneWindowTop.setOnMouseEntered(event -> {isDraggable = true;});
        // gPaneWindowTop.setOnMouseExited (event -> {isDraggable = false;});
        gPaneWindowTop.setOnMouseMoved(draggingControl());
        gPaneWindowTop.setOnMousePressed(pressingControl());
        gPaneWindowTop.setOnMouseDragged(doDragging());
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



    protected void setStyle(){
        buttonClose.getStyleClass().setAll("button-close");
        buttonMaximize.getStyleClass().setAll("button-maximize");
        buttonMinimize.getStyleClass().setAll("button-minimize");
    }

}