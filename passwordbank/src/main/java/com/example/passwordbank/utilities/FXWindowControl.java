package com.example.passwordbank.utilities;

import com.example.passwordbank.App;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXWindowControl {

    private final Stage primaryStage;
    private final double mouseResizeOffset = 2;
    private final double mouseDragOffset = mouseResizeOffset + 25;
    private final Button bMinimize, bMaximize, bClose;
    private Pane parentNode;
    private Scene activeScene;
    
    private boolean moveNorth,      moveSouth;
    private boolean moveEast,       moveWest;
    private boolean isResizable,    isDraggable;
    
    private double mouseXPos,         mouseYPos;
    private double stageWidth,      stageHeight;
    private double lastMouseXPos, lastMouseYPos;
    private double lastStageXPos, lastStageYPos;
    private double moveOffSetX,     moveOffSetY;
    private ResizeDirection dir;




    
    public FXWindowControl(Button minimize, Button maximize, Button close) {
        bClose = close; bMaximize = maximize; bMinimize = minimize;
        primaryStage = App.getStage();
        activeScene = primaryStage.getScene();
        parentNode = (Pane) activeScene.getRoot();
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
        bClose.getStyleClass().setAll("button-close");
        bMaximize.getStyleClass().setAll("button-maximize");
        bMinimize.getStyleClass().setAll("button-minimize");

        windowMainOperations();

        parentNode.setOnMouseMoved(positionControl());
        parentNode.setOnMousePressed(event -> {
            lastMouseXPos = event.getScreenX();
            lastMouseYPos = event.getScreenY();
            lastStageXPos = primaryStage.getX();
            lastStageYPos = primaryStage.getY();
            stageHeight = primaryStage.getHeight();
            stageWidth = primaryStage.getWidth();
            moveOffSetX = primaryStage.getX() - event.getScreenX();
            moveOffSetY = primaryStage.getY() - event.getScreenY();
        });
        parentNode.setOnMouseDragged(doStageAction());
        parentNode.setOnMouseReleased(event -> {
            if (primaryStage.getHeight() < App.defH) {
                primaryStage.setHeight(App.defH);
            }
            if (primaryStage.getWidth() < App.defW) {
                primaryStage.setWidth(App.defW);
            }
        });
        parentNode.setOnMouseClicked(toggleMaximize());
    }



    private void windowMainOperations() {
        activeScene.setOnKeyPressed(event -> {
            if (event.isAltDown() && 
                event.getCode().equals(KeyCode.F4)) 
                closeApplication();
        });
        bClose.setOnMouseClicked(event -> closeApplication());
        bMaximize.setOnMouseClicked(event -> doMaximizing());
        bMinimize.setOnMouseClicked(event -> primaryStage.setIconified(true));
    }



    private EventHandler<MouseEvent> toggleMaximize() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if ((arg0.getClickCount() == 2) && 
                    (arg0.getSceneY() <= 25) &&
                    (arg0.getButton() == MouseButton.PRIMARY)) {
                    doMaximizing();
                }
            }
        };
    }


    private void doMaximizing() {
        if (primaryStage.isMaximized()) {
            primaryStage.setWidth(App.defW);
            primaryStage.setHeight(App.defH);
            setScenePadding(true);
            primaryStage.setMaximized(false);
        } else {
            setScenePadding(false);
            primaryStage.setMaximized(true);
        }
    }


    private final void closeApplication() {
        Platform.exit();
    }
    

    private EventHandler<MouseEvent> positionControl() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                {
                    isResizable = isDraggable = false;
                    moveNorth = moveEast = moveSouth = moveWest = false;
                    parentNode.setCursor(Cursor.DEFAULT);
                }
                
                stageHeight = primaryStage.getHeight();
                stageWidth = primaryStage.getWidth();
                mouseXPos = arg0.getSceneX();
                mouseYPos = arg0.getSceneY();
                

                if (!primaryStage.isMaximized()) {
                    if ((mouseYPos <= mouseDragOffset) && 
                        (mouseYPos > mouseResizeOffset)) {
                        isDraggable = true;
                        return;
                    }

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



    private EventHandler<MouseEvent> doStageAction() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (isResizable && (arg0.getButton() == MouseButton.PRIMARY)) {
                    double newHeight, newWidth;
                    switch (dir) {
                        case N:
                            newHeight = stageHeight - (arg0.getScreenY() - lastMouseYPos);
                            
                            if (newHeight > App.defH) {
                                primaryStage.setY(arg0.getScreenY());
                                primaryStage.setHeight(newHeight);
                                lastStageYPos = arg0.getScreenY();
                            } else {
                                primaryStage.setY(lastStageYPos);
                                primaryStage.setHeight(newHeight);
                                if (arg0.getScreenY() == lastStageYPos) {
                                    primaryStage.setHeight(App.defH);
                                }
                            }
                            stageHeight = primaryStage.getHeight();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case E:
                            newWidth = stageWidth + (arg0.getScreenX() - lastMouseXPos);

                            if (newWidth > App.defW) primaryStage.setWidth(newWidth);
                            else primaryStage.setWidth(newWidth);

                            stageWidth = primaryStage.getWidth();
                            lastMouseXPos = arg0.getScreenX();
                            break;

                        case S:
                            newHeight = stageHeight + (arg0.getScreenY() - lastMouseYPos);

                            if (newHeight > App.defH) primaryStage.setHeight(newHeight);
                            else primaryStage.setHeight(newHeight);
                            
                            stageHeight = primaryStage.getHeight();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case W:
                            newWidth = primaryStage.getWidth() - (arg0.getScreenX() - lastMouseXPos);
                            
                            if (newWidth > App.defW) {
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

                            if (newHeight > App.defH) {
                                primaryStage.setY(arg0.getScreenY());
                                primaryStage.setHeight(newHeight);
                                lastStageYPos = arg0.getScreenY();
                            } else {
                                primaryStage.setY(lastStageYPos);
                                primaryStage.setHeight(newHeight);
                                if (arg0.getScreenY() == lastStageYPos) {
                                    primaryStage.setHeight(App.defH);
                                }
                            }

                            if (newWidth > App.defW) primaryStage.setWidth(newWidth);
                            else primaryStage.setWidth(newWidth);

                            stageHeight = primaryStage.getHeight();
                            stageWidth  = primaryStage.getWidth();
                            lastMouseYPos = arg0.getScreenY();
                            lastMouseXPos = arg0.getScreenX();
                            break;

                        case NW:
                            newHeight = stageHeight - (arg0.getScreenY() - lastMouseYPos);
                            newWidth  = stageWidth  - (arg0.getScreenX() - lastMouseXPos);
                            
                            if (newHeight > App.defH) {
                                primaryStage.setY(arg0.getScreenY());
                                primaryStage.setHeight(newHeight);
                                lastStageYPos = arg0.getScreenY();
                            } else {
                                primaryStage.setY(lastStageYPos);
                                primaryStage.setHeight(newHeight);
                                if (arg0.getScreenY() == lastStageYPos) {
                                    primaryStage.setHeight(App.defH);
                                }
                            }

                            if (newWidth > App.defW) {
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

                            if (newHeight > App.defH) primaryStage.setHeight(newHeight);
                            else primaryStage.setHeight(newHeight);
                            if (newWidth > App.defW) primaryStage.setWidth(newWidth);
                            else primaryStage.setWidth(newWidth);
                            
                            stageHeight = primaryStage.getHeight();
                            stageWidth  = primaryStage.getWidth();
                            lastMouseXPos = arg0.getScreenX();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        case SW:
                            newHeight = stageHeight + (arg0.getScreenY() - lastMouseYPos);
                            newWidth = stageWidth - (arg0.getScreenX() - lastMouseXPos);

                            
                            if (newHeight > App.defH) primaryStage.setHeight(newHeight);
                            else primaryStage.setHeight(newHeight);

                            if (newWidth > App.defW) {
                                primaryStage.setX(arg0.getScreenX());
                                primaryStage.setWidth(newWidth);
                                lastStageXPos = arg0.getScreenX();
                            } else {
                                primaryStage.setX(lastStageXPos);
                                primaryStage.setWidth(newWidth);
                            }
                            
                            
                            stageHeight = primaryStage.getHeight();
                            stageWidth = primaryStage.getWidth();
                            lastMouseXPos = arg0.getScreenX();
                            lastMouseYPos = arg0.getScreenY();
                            break;

                        default:
                            break;
                    };
                }

                if (isDraggable && arg0.getButton().equals(MouseButton.PRIMARY)) {
                    primaryStage.setX(arg0.getScreenX() + moveOffSetX);
                    primaryStage.setY(arg0.getScreenY() + moveOffSetY);
                }
            };
        };
    }




    private void setScenePadding(boolean set) {
        if (set) parentNode.setPadding(new Insets(mouseResizeOffset));
        else     parentNode.setPadding(new Insets(0));
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
