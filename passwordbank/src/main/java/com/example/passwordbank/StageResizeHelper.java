package com.example.passwordbank;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StageResizeHelper {


    private double mouseXPos;
    private double mouseYPos;
    private double mousePressX;
    private double mousePressY;
    private double stageXPos;
    private double stageYPos;
    private final double screenHSize;
    private final double screenWSize;
    private final double mouseResizeOffset = 25;
    private final double mouseDragOffset = 10;
    private boolean isDraggable;
    private boolean isResizable;
    private MouseEvent moveNorth;
    private MouseEvent moveSouth;
    private MouseEvent moveEast;
    private MouseEvent moveWest;
    private MouseEvent moveNorthEast;
    private MouseEvent moveNorthWest;
    private MouseEvent moveSouthEast;
    private MouseEvent moveSouthWest;
    private final Pane mainPane;
    private final Stage primaryStage;
    
    
    
    
    public StageResizeHelper(final Stage stage, Pane mainPane) {
        this.primaryStage = stage;
        this.mainPane = mainPane;
        screenHSize = Screen.getPrimary().getVisualBounds().getHeight();
        screenWSize = Screen.getPrimary().getVisualBounds().getWidth();

        
        mouseTracking();
    }




    private void mouseTracking() {
        mainPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                mouseXPos = arg0.getX();
                mouseYPos = arg0.getY();

                if ((mouseYPos - stageYPos) <= mouseDragOffset) {
                    mainPane.setCursor(Cursor.HAND);
                    isDraggable = true;
                } else {
                    isDraggable = false;
                    mainPane.setCursor(Cursor.DEFAULT);
                }

                if (((stageXPos - mouseXPos) <= mouseResizeOffset)) {
                    
                }
            }
        });
        mainPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
            }
            
        });
    }

    

    private void MouseEvents(){
        
    }

    
}
