package com.example.passwordbank.utilities;

import java.io.IOException;
import java.net.URL;

import com.example.passwordbank.App;
// import com.example.passwordbank.controllers.ModalPasswordController;
import com.example.passwordbank.model.Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ModalManager {

    Login managedLogin;
    URL modalFile = App.class.getResource("modal");
    Pane modalRoot;
    Node rootNode;



    public ModalManager(Login login, Node rootNode) {
        loadModal();
        managedLogin = login;
        this.rootNode = rootNode;
    }



    private void loadModal() {
        try {
            FXMLLoader loader = new FXMLLoader(modalFile);
            modalRoot = (Pane) loader.load();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void showModal() {
        Stage modalStage = new Stage();
        modalStage.setResizable(false);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UNDECORATED);

        Window appWindow = rootNode.getScene().getWindow();
        double modalX, modalY;
        modalX = ((appWindow.getX() + appWindow.getWidth()/2) - (modalRoot.getWidth()/2));
        modalY = ((appWindow.getY() + appWindow.getHeight()/2) - (modalRoot.getHeight()/2));
        modalStage.setX(modalX);
        modalStage.setY(modalY);

        Scene modalScene = new Scene(modalRoot);
        modalStage.setScene(modalScene);
        
        // ModalPasswordController.setLoginToShow(managedLogin);

        modalStage.showAndWait();

        // setLoginUpdated(ModalPasswordController.getLogin());
    }


    public Login getLoginUpdated() {
        return this.managedLogin;
    }

    // private void setLoginUpdated(Login updatedLogin) {
    //     this.managedLogin = updatedLogin;
    // }
}
