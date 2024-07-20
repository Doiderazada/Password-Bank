package com.example.passwordbank.utilities;

import java.io.IOException;

import com.example.passwordbank.App;
import com.example.passwordbank.controllers.ModalController;
import com.example.passwordbank.model.Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModalManager {

    private Login managedLogin;
    private Pane modalRoot;
    private Node rootNode;
    private ModalState state;
    private ModalController controller;



    public ModalManager(Login login, Node rootNode, ModalState state) {
        loadModal();
        this.state = state;
        this.managedLogin = login;
        this.rootNode = rootNode;
    }



    private void loadModal() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("views/modal.fxml"));
            modalRoot = (Pane) loader.load();
            controller = loader.getController();

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }


    public void showModal() {
        Stage modalStage = new Stage();
        modalStage.setResizable(false);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.TRANSPARENT);

        Scene modalScene;
        modalScene = new Scene(modalRoot);
        modalScene.setFill(Color.rgb(0, 0, 0, 0.01));
        modalStage.setScene(modalScene);
        modalStage.sizeToScene();
        
        Stage appWindow = (Stage) rootNode.getScene().getWindow();
        double modalX, modalY;
        modalX = ((appWindow.getX() + appWindow.getWidth()/2) - 200 /* <- half modal width */);
        modalY = ((appWindow.getY() + appWindow.getHeight()/2) - 200 /* <- half modal height*/);
        modalStage.setX(modalX);
        modalStage.setY(modalY);


        switch (state) {
            case CREATE:
                ModalController.setLoginToShow(managedLogin);
                modalStage.showAndWait();
                if (ModalController.getLogin() != null) {
                    setLoginUpdated(ModalController.getLogin());
                    ModalController.setLoginToShow(null);
                }
                break;
                
                case EDIT:
                ModalController.setLoginToShow(managedLogin);
                controller.setFields();
                modalStage.showAndWait();
                setLoginUpdated(ModalController.getLogin());
                ModalController.setLoginToShow(null);
            break;
        }
    }


    public Login getLoginUpdated() {
        return this.managedLogin;
    }

    private void setLoginUpdated(Login updatedLogin) {
        this.managedLogin = updatedLogin;
    }
}
