package com.example.passwordbank;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WindowControlHelper {

    
    private final double mouseResizeOffset = 2;
    private final Stage primaryStage = App.getStage();
    private final Button bMinimize, bMaximize, bClose;
    private Scene activeScene;
    private Parent parentNode;
    private Pane mainPane;
    
    private boolean isResizable;
    private boolean moveNorth, moveSouth;
    private boolean moveEast, moveWest;
    
    private double mouseXPos,         mouseYPos;
    private double stageWidth,      stageHeight;
    private double lastMouseXPos, lastMouseYPos;
    private double lastStageXPos, lastStageYPos;
    private ResizeDirection dir;




    
    public WindowControlHelper(Pane mainPane, Button minimize, Button maximize, Button close) {
        bClose = close; bMaximize = maximize; bMinimize = minimize;
        this.mainPane = mainPane;

        activeScene = primaryStage.getScene();
        parentNode = activeScene.getRoot();
        setScenePadding(true);
        setActions();
        tips();
    }




    private enum ResizeDirection {
        N, E, S, W, 
        NE, NW, SE, SW;
    }



    private void tips() {
        Tooltip closeTip = new Tooltip("Close");
        closeTip.setShowDelay(Duration.millis(500));
        closeTip.setAutoHide(false);
        bClose.setTooltip(closeTip);

        Tooltip maxTip = new Tooltip("Maximize");
        maxTip.setShowDelay(Duration.millis(500));
        maxTip.setAutoHide(false);
        bMaximize.setTooltip(maxTip);
        
        Tooltip minTip = new Tooltip("Minimize");
        minTip.setShowDelay(Duration.millis(500));
        minTip.setAutoHide(false);
        bMinimize.setTooltip(minTip);
    }




    private void setActions() {
        bClose.setOnMouseClicked(event -> {primaryStage.close();});
        bMaximize.setOnMouseClicked(maximizeActicons());
        bMinimize.setOnMouseClicked(event -> {primaryStage.setIconified(true);});

        parentNode.setOnMouseMoved(resizeControl());
        parentNode.setOnMousePressed(event -> {
            lastMouseXPos = event.getScreenX();
            lastMouseYPos = event.getScreenY();
            lastStageXPos = primaryStage.getX();
            lastStageYPos = primaryStage.getY();
            stageHeight = primaryStage.getHeight();
            stageWidth = primaryStage.getWidth();
        });
        parentNode.setOnMouseDragged(doResizing());
        parentNode.setOnMouseReleased(event -> {
            if (primaryStage.getHeight() < App.minH) {
                primaryStage.setHeight(App.minH);
            }
            if (primaryStage.getWidth() < App.minW) {
                primaryStage.setWidth(App.minW);
            }
        });
    }



    
    private EventHandler<MouseEvent> maximizeActicons() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (primaryStage.isMaximized()) {
                    primaryStage.setWidth(App.minW);
                    primaryStage.setHeight(App.minH);
                    primaryStage.centerOnScreen();
                    setScenePadding(true);
                    primaryStage.setMaximized(false);
                } else {
                    setScenePadding(false);
                    primaryStage.setMaximized(true);
                    primaryStage.centerOnScreen();
                }
            }
        };
    }




    private EventHandler<MouseEvent> resizeControl() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                {
                    isResizable = false;
                    moveNorth = moveEast = moveSouth = moveWest = false;
                    mainPane.setCursor(Cursor.DEFAULT);
                }
                
                stageHeight = primaryStage.getHeight();
                stageWidth = primaryStage.getWidth();
                mouseXPos = arg0.getSceneX();
                mouseYPos = arg0.getSceneY();
                

                if (!primaryStage.isMaximized()) {
                    if (mouseYPos <= mouseResizeOffset) {
                        isResizable = moveNorth = true;
                        moveSouth = !moveNorth;
                    } else moveNorth = false;

                    if (stageHeight - mouseYPos <= mouseResizeOffset) {
                        isResizable = moveSouth = true;
                        moveNorth = !moveSouth;
                    } else moveSouth = false;
                    
                    if (mouseXPos <= mouseResizeOffset) {
                        isResizable = moveWest = true;
                        moveEast = !moveWest;
                    } else moveWest = false;

                    if ((stageWidth - mouseXPos) <= mouseResizeOffset) {
                        isResizable = moveEast = true;
                        moveWest = !moveEast;
                    } else moveEast = false;
                }

                setDirectionToResize();
            };
        };
    }



    private EventHandler<MouseEvent> doResizing() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (isResizable && (arg0.getButton() == MouseButton.PRIMARY)) {
                    double newHeight, newWidth;
                    switch (dir) {
                        case N:
                            newHeight = stageHeight - (arg0.getScreenY() - lastMouseYPos);
                            
                            if (newHeight > App.minH) {
                                primaryStage.setY(arg0.getScreenY());
                                primaryStage.setHeight(newHeight);
                                lastStageYPos = arg0.getScreenY();
                            } else {
                                primaryStage.setY(lastStageYPos);
                                primaryStage.setHeight(newHeight);
                                if (arg0.getScreenY() == lastStageYPos) {
                                    primaryStage.setHeight(App.minH);
                                }
                            }
                            stageHeight = primaryStage.getHeight();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case E:
                            newWidth = stageWidth + (arg0.getScreenX() - lastMouseXPos);

                            if (newWidth > App.minW) primaryStage.setWidth(newWidth);
                            else primaryStage.setWidth(newWidth);

                            stageWidth = primaryStage.getWidth();
                            lastMouseXPos = arg0.getScreenX();
                            break;

                        case S:
                            newHeight = stageHeight + (arg0.getScreenY() - lastMouseYPos);

                            if (newHeight > App.minH) primaryStage.setHeight(newHeight);
                            else primaryStage.setHeight(newHeight);
                            
                            stageHeight = primaryStage.getHeight();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case W:
                            newWidth = primaryStage.getWidth() - (arg0.getScreenX() - lastMouseXPos);
                            
                            if (newWidth > App.minW) {
                                primaryStage.setX(arg0.getScreenX());
                                primaryStage.setWidth(newWidth);
                                lastStageXPos = arg0.getScreenX();
                            } else {
                                primaryStage.setX(lastStageXPos);
                                primaryStage.setWidth(newWidth);
                            }
                            
                            lastMouseXPos = arg0.getScreenX();
                            break;

                        case NE:
                            newHeight = stageHeight - (arg0.getScreenY() - lastMouseYPos);
                            newWidth  = stageWidth  + (arg0.getScreenX() - lastMouseXPos);

                            if (newHeight > App.minH) {
                                primaryStage.setY(arg0.getScreenY());
                                primaryStage.setHeight(newHeight);
                                lastStageYPos = arg0.getScreenY();
                            } else {
                                primaryStage.setY(lastStageYPos);
                                primaryStage.setHeight(newHeight);
                                if (arg0.getScreenY() == lastStageYPos) {
                                    primaryStage.setHeight(App.minH);
                                }
                            }

                            if (newWidth > App.minW) primaryStage.setWidth(newWidth);
                            else primaryStage.setWidth(newWidth);

                            stageHeight = primaryStage.getHeight();
                            stageWidth  = primaryStage.getWidth();
                            lastMouseYPos = arg0.getScreenY();
                            lastMouseXPos = arg0.getScreenX();
                            break;

                        case NW:
                            newHeight = stageHeight - (arg0.getScreenY() - lastMouseYPos);
                            newWidth  = stageWidth  - (arg0.getScreenX() - lastMouseXPos);
                            
                            if (newHeight > App.minH) {
                                primaryStage.setY(arg0.getScreenY());
                                primaryStage.setHeight(newHeight);
                                lastStageYPos = arg0.getScreenY();
                            } else {
                                primaryStage.setY(lastStageYPos);
                                primaryStage.setHeight(newHeight);
                                if (arg0.getScreenY() == lastStageYPos) {
                                    primaryStage.setHeight(App.minH);
                                }
                            }

                            if (newWidth > App.minW) {
                                primaryStage.setX(arg0.getScreenX());
                                primaryStage.setWidth(newWidth);
                                lastStageXPos = arg0.getScreenX();
                            } else {
                                primaryStage.setX(lastStageXPos);
                                primaryStage.setWidth(newWidth);
                            }  
                            
                            stageHeight = primaryStage.getHeight();
                            stageWidth  = primaryStage.getWidth();
                            lastMouseXPos = arg0.getScreenX();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case SE:
                            newHeight = stageHeight + (arg0.getScreenY() - lastMouseYPos);
                            newWidth = stageWidth + (arg0.getScreenX() - lastMouseXPos);

                            if (newHeight > App.minH) primaryStage.setHeight(newHeight);
                            else primaryStage.setHeight(newHeight);
                            if (newWidth > App.minW) primaryStage.setWidth(newWidth);
                            else primaryStage.setWidth(newWidth);
                            
                            stageHeight = primaryStage.getHeight();
                            stageWidth  = primaryStage.getWidth();
                            lastMouseXPos = arg0.getScreenX();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case SW:
                            newHeight = stageHeight + (arg0.getScreenY() - lastMouseYPos);
                            newWidth = primaryStage.getWidth() - (arg0.getScreenX() - lastMouseXPos);
                            
                            if (newHeight > App.minH) primaryStage.setHeight(newHeight);
                            else primaryStage.setHeight(newHeight);

                            if (newWidth > App.minW) {
                                primaryStage.setX(arg0.getScreenX());
                                primaryStage.setWidth(newWidth);
                                lastStageXPos = arg0.getScreenX();
                            } else {
                                primaryStage.setX(lastStageXPos);
                                primaryStage.setWidth(newWidth);
                            }
                            
                            stageHeight = primaryStage.getHeight();
                            lastMouseXPos = arg0.getScreenX();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        default:
                            break;
                    };
                };
            };
        };
    }




    private void setScenePadding(boolean set) {
        if (set) {
            mainPane.setPadding(new Insets(mouseResizeOffset));
            // activeScene.setFill(Color.rgb(0, 0, 0, 0.02));
        } else {
            mainPane.setPadding(new Insets(0));
            // activeScene.setFill(null);
        }
    }



    private void setDirectionToResize() {
        if (moveNorth) {
            dir = ResizeDirection.N;
            parentNode.setCursor(Cursor.N_RESIZE);
            if (moveEast) {
                dir = ResizeDirection.NE;
                parentNode.setCursor(Cursor.NE_RESIZE);
            }
            if (moveWest) {
                dir = ResizeDirection.NW;
                parentNode.setCursor(Cursor.NW_RESIZE);
            }
            return;
        }
        if (moveSouth) {
            dir = ResizeDirection.S;
            parentNode.setCursor(Cursor.S_RESIZE);
            if (moveEast) {
                dir = ResizeDirection.SE;
                parentNode.setCursor(Cursor.SE_RESIZE);
            }  
            if (moveWest) {
                dir = ResizeDirection.SW;
                parentNode.setCursor(Cursor.SW_RESIZE);
            }
            return;
        }
        if (moveEast) {
            dir = ResizeDirection.E;
            parentNode.setCursor(Cursor.E_RESIZE);
        }
        if (moveWest) {
            dir = ResizeDirection.W;
            parentNode.setCursor(Cursor.W_RESIZE);
        }
        return;
    }
}
