package com.example.passwordbank.utilities;

import javax.swing.border.StrokeBorder;

import com.example.passwordbank.App;
import com.example.passwordbank.model.Login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PasswordFXElement {

    private Button buttonEdit;
    private Button buttonDelete;
    private Button bViewPass;
    private HBox hBoxButtons;
    private Label labelPass;
    private Label labelUser;
    private static Rectangle mainRectangle;
    private StackPane mainStackPane;
    private StackPane passStackPane;
    private PasswordField pFPass;
    private Text textIdentifier;
    private TextField tFPass;
    private TextField tFUser;
    private VBox mainVBox;
    private VBox fieldVBox;
    private VBox userVBox;
    private VBox passVBox;

    private final double tFieldW = 300;
    private final double tFieldH = 30; 
    private final double innVBoxW = 300;
    private final double innVBoxH = 60;

    private final String cssFile = "styles/buttons.css";
    private Login login;



    public PasswordFXElement(Login login) {
        this.login = login;
        createComponents();
        setComponents();
        setActions();
        completeFields();
    }



    private void createComponents() {
        buttonDelete  = new Button();
        buttonEdit    = new Button();
        bViewPass     = new Button();
        hBoxButtons   = new HBox();
        labelPass     = new Label();
        labelUser     = new Label();
        mainRectangle = new Rectangle();
        mainStackPane = new StackPane();
        passStackPane = new StackPane();
        pFPass        = new PasswordField();
        textIdentifier = new Text();
        tFPass         = new TextField();
        tFUser         = new TextField();
        mainVBox       = new VBox();
        fieldVBox      = new VBox();
        userVBox       = new VBox();
        passVBox       = new VBox();
    }

    private void setComponents() {
        mainStackPane.setAlignment(Pos.CENTER);
        mainStackPane.setPrefSize(350, 250);
        mainStackPane.setMinHeight(mainStackPane.getPrefHeight());
        mainStackPane.setMinWidth(mainStackPane.getPrefWidth());
        mainStackPane.setMaxHeight(mainStackPane.getPrefHeight());
        mainStackPane.setMaxWidth(mainStackPane.getPrefWidth());
        mainStackPane.setFocusTraversable(false);
        mainStackPane.setPrefSize(350, 250);

        mainRectangle.setWidth(350);
        mainRectangle.setHeight(250);
        mainRectangle.setFocusTraversable(false);
        mainRectangle.setArcWidth(20);
        mainRectangle.setArcHeight(20);
        mainRectangle.setFill(Color.valueOf("#8A59EE"));
        setStokeByTheme();
        mainRectangle.setStrokeWidth(1.5);
        mainRectangle.setStrokeType(StrokeType.INSIDE);
        mainRectangle.setSmooth(true);
        
        mainVBox.setAlignment(Pos.TOP_LEFT);
        mainVBox.setSpacing(15);
        
        VBox.setMargin(textIdentifier, new Insets(10, 0, 0, 15));
        textIdentifier.setText("Identifier");
        textIdentifier.setFont(Font.font(null, FontWeight.BOLD, 20));
        textIdentifier.setFocusTraversable(false);

        fieldVBox.setAlignment(Pos.CENTER);
        fieldVBox.setSpacing(15);
        
        userVBox.setPrefSize(innVBoxW, innVBoxH);
        userVBox.setMinWidth(innVBoxW);
        userVBox.setMinHeight(innVBoxH);
        userVBox.setMaxWidth(innVBoxW);
        userVBox.setMaxHeight(innVBoxH);
        
        labelUser.setFocusTraversable(false);
        labelUser.setText("Login");
        labelUser.setFont(Font.font(15));
        
        tFUser.setPrefSize(tFieldW, tFieldH);
        tFUser.setMinSize(tFieldW, tFieldH);
        tFUser.setMaxSize(tFieldW, tFieldH);
        tFUser.setEditable(false);
        
        passVBox.setPrefSize(innVBoxW, innVBoxH);
        passVBox.setMinWidth(innVBoxW);
        passVBox.setMinHeight(innVBoxH);
        passVBox.setMaxWidth(innVBoxW);
        passVBox.setMaxHeight(innVBoxH);

        labelPass.setFocusTraversable(false);
        labelPass.setText("Password");
        labelPass.setFont(Font.font(15));

        passStackPane.setAlignment(Pos.CENTER);
        passStackPane.setPrefSize(tFieldW, tFieldH);
        
        tFPass.setPrefSize(tFieldW, tFieldH);
        tFPass.setMinSize(tFieldW, tFieldH);
        tFPass.setMaxSize(tFieldW, tFieldH);
        tFPass.setEditable(false);
        
        pFPass.setPrefSize(tFieldW, tFieldH);
        pFPass.setMinSize(tFieldW, tFieldH);
        pFPass.setMaxSize(tFieldW, tFieldH);
        pFPass.setText("Minha princess");
        pFPass.setEditable(false);

        bViewPass.setPrefSize(tFieldH, tFieldH);
        bViewPass.getStylesheets().setAll(App.class.getResource(cssFile).toExternalForm());
        bViewPass.getStyleClass().setAll("button-HidePass");
        StackPane.setAlignment(bViewPass, Pos.CENTER_RIGHT);

        hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxButtons.setSpacing(20);
        hBoxButtons.setPadding(new Insets(0, 25, 0, 0));

        buttonDelete.setPrefSize(30, 30);
        buttonDelete.getStylesheets().setAll(App.class.getResource(cssFile).toExternalForm());
        buttonDelete.getStyleClass().setAll("button-DeletePass");
        buttonEdit.setPrefSize(30, 30);
        buttonEdit.getStylesheets().setAll(App.class.getResource(cssFile).toExternalForm());
        buttonEdit.getStyleClass().setAll("button-EditPass");

        
        mainStackPane.getChildren().addAll(mainRectangle, mainVBox);
        mainVBox.getChildren().addAll(textIdentifier, fieldVBox);
        fieldVBox.getChildren().addAll(userVBox, passVBox, hBoxButtons);
        userVBox.getChildren().addAll(labelUser,  tFUser);
        passVBox.getChildren().addAll(labelPass, passStackPane);
        hBoxButtons.getChildren().addAll(buttonEdit, buttonDelete);
        passStackPane.getChildren().addAll(tFPass, pFPass, bViewPass);
    }



    private void setActions() {

        bViewPass.setOnMousePressed(event -> {
            this.login.increaseCount();
            tFPass.setText(pFPass.getText());
            pFPass.setVisible(false);
            bViewPass.getStyleClass().setAll("button-ViewPass");
        });
        bViewPass.setOnMouseReleased(event -> {
            tFPass.clear();
            pFPass.setVisible(true);
            bViewPass.getStyleClass().setAll("button-HidePass");
        });

        tFUser.setOnMouseClicked(event -> {
            this.login.increaseCount();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(tFUser.getText());
            clipboard.setContent(content);

            
            Tooltip tip = new Tooltip("content copied to clipboard");
            Tooltip.install(tFUser, tip);
            tip.setAutoHide(true);
            tip.setShowDuration(Duration.millis(800));
            tip.show(bViewPass, event.getScreenX(), event.getScreenY());
        });

        buttonEdit.setOnMouseClicked(event -> {
            ModalManager modal = new ModalManager(this.login, bViewPass, ModalState.EDIT);
            modal.showModal();
            this.login = modal.getLoginUpdated();
            completeFields();
        });
        buttonDelete.setOnMouseClicked(event -> {
            GridPane parent = (GridPane) this.mainStackPane.getParent();
            GridPane.clearConstraints(this.mainStackPane);
            parent.getChildren().remove(this.mainStackPane);
            App.logs.remove(login);
            login = null;
            this.mainStackPane = null;
        });
    }

    private void completeFields() {
        setIdentifier(login.getIdentifier());
        setUser(login.getUserName());
        setPassword(login.getPassword().getPass());
    }

    public void setIdentifier(String identifier) {
        this.textIdentifier.setText(identifier);
    }

    public void setPassword(String password) {
        this.pFPass.setText(password);
    }

    public void setUser(String userName) {
        this.tFUser.setText(userName);
    }


    public Node getRoot() {
        return this.mainStackPane;
    }



    public static void setStokeByTheme() {
        if (App.darkMode) 
             mainRectangle.setStroke(Color.WHITE);
        else mainRectangle.setStroke(Color.BLACK);
    }
}