package com.example.passwordbank.controllers;

import java.io.IOException;

import com.example.passwordbank.App;
import com.example.passwordbank.model.AppUser;
import com.example.passwordbank.model.Password;

import animatefx.animation.Shake;
import animatefx.animation.SlideAnimation;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartScreenController {

    /*
     * Start screen components
     */
    @FXML private Button bViewPass;
    @FXML private Button buttonClose;
    @FXML private Button buttonLogReg;
    @FXML private CheckBox cBoxStayLogged;
    @FXML private Label lHintPasswordStart;
    @FXML private Label lHintUsernameStart;
    @FXML private Label lPassword;
    @FXML private Label lUsername;
    @FXML private Pane pLogin;
    @FXML private PasswordField pFPassword;
    @FXML private StackPane stackPaneMain;
    @FXML private TextField tFPassword;
    @FXML private TextField tFUsername;
    @FXML private Text tForgotPass;
    @FXML private Text tWelcome;
    @FXML private VBox vBoxUserInfo;

    /*
     * Register screen 1 components
     */
    @FXML private Button bViewPassReg;
    @FXML private Button bViewPassRepReg;
    @FXML private Button buttonBackStartPage;
    @FXML private Button buttonNextPageReg;
    @FXML private Label lEmailReg;
    @FXML private Label lHintEmailReg;
    @FXML private Label lHintPassReg;
    @FXML private Label lHintPassRepReg;
    @FXML private Label lPasswordReg;
    @FXML private Label lPasswordRepReg;
    @FXML private PasswordField pFPasswordReg;
    @FXML private PasswordField pFPasswordRepReg;
    @FXML private TextField tFEmailReg;
    @FXML private TextField tFPasswordReg;
    @FXML private TextField tFPasswordRepReg;
    @FXML private Text textRegInfo;

    /*
     * Register screen 2 components
     */
    @FXML private Button buttonBackPageReg;
    @FXML private Button buttonNextPageRecovery;
    @FXML private CheckBox cBoxUsername;
    @FXML private Label lHintUsernameReg;
    @FXML private Label labelUsernameReg;
    @FXML private Pane paneRegister;
    @FXML private TextField tFUsernameReg;
    @FXML private Text textRegInfo2;

    /*
     * Recovery information screen presentation
     */
    @FXML private Button buttonNextPageRecovery2;
    @FXML private Button buttonSkipRecovery;
    @FXML private Text textRegRec1;

    /*
     * Recovery information screen 1
     */
    @FXML private Button buttonNextPageRecovery3;
    @FXML private CheckBox cBoxAltEmail;
    @FXML private Label lAltEmailRec;
    @FXML private Label lHintAltEmailRec;
    @FXML private Label lHintMainEmailRec;
    @FXML private Label lHintPhoneNum;
    @FXML private Label lMainEmailRec;
    @FXML private Label lPhoneNum;
    @FXML private TextField tFAltEmailRec;
    @FXML private TextField tFMainEmailRec;
    @FXML private TextField tFPhoneNum;
    @FXML private Text textRegRec2;

    /*
     * Recovery information screen 2
     */
    @FXML private Button buttonBackPageRec3;
    @FXML private Button buttonFinish;
    @FXML private ChoiceBox<String> cBoxQuestion1;
    @FXML private ChoiceBox<String> cBoxQuestion2;
    @FXML private ChoiceBox<String> cBoxQuestion3;
    @FXML private Label lAnswer1;
    @FXML private Label lAnswer2;
    @FXML private Label lAnswer3;
    @FXML private Label lHintQuestions;
    @FXML private Label lQuestion1;
    @FXML private Label lQuestion2;
    @FXML private Label lQuestion3;
    @FXML private TextArea tAAnswer1;
    @FXML private TextArea tAAnswer2;
    @FXML private TextArea tAAnswer3;
    @FXML private Text textRegRec3;


    private Pane panePage1;
    private Pane panePage2;
    private Pane panePage3;
    private Pane panePage4;
    private Pane panePage5;

    private final String tFieldErrorStyle = "-fx-border-color: red;";
    private final String labelStyleGood   = "-fx-text-fill: green;";
    private final String labelStyleBad    = "-fx-text-fill: red;";
    private final String emailRegexGroup  = "((?>@gmail\\.com)|(?>@outlook\\.com)|(?>@yahoo\\.com)|(?>@hotmail\\.com))$";
    private final String genericRegexText = "^[\\w^(.\\-_)]+";
    private AppUser user;
    private String password;
    private String username;
    private String mainEmail;
    private String altEmail;
    private String phoneNum;
    private String question1;
    private String answer1;
    private String question2;
    private String answer2;
    private String question3;
    private String answer3;
    

    public void initialize() {
        App.getStage().setOnShown(event -> {
            if (!App.haveUser) loadNextPages();
        });
        if (App.haveUser) user = App.user;
        startPageActions();
    }



    

    private void startPageActions() {
        buttonLogReg.getStyleClass().setAll("button-LogReg");
        bViewPass.getStyleClass().setAll("button-HidePass");

        bViewPass.setOnMousePressed(event -> {
            bViewPass.requestFocus();
            String pass = pFPassword.getText();
            tFPassword.setText(pass);
            pFPassword.setVisible(false);
            tFPassword.setVisible(true);
            bViewPass.getStyleClass().setAll("button-ViewPass");
        });
        bViewPass.setOnMouseReleased(event -> {
            bViewPass.focusedProperty().not();
            String pass = tFPassword.getText();
            pFPassword.setText(pass);
            tFPassword.setVisible(false);
            pFPassword.setVisible(true);
            bViewPass.getStyleClass().setAll("button-HidePass");
        });
        buttonClose.setOnMouseClicked(event -> Platform.exit());
        buttonClose.setOnMouseMoved(event -> buttonClose.setCursor(Cursor.HAND));
         
        if (App.haveUser) {
            buttonLogReg.setText("Login");
            buttonLogReg.setOnMouseClicked(event -> {if (verifyStartFields()) goToApp();});
            buttonLogReg.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER) && verifyStartFields()) 
                    {goToApp();}
            });
        } else {
            tWelcome.setLayoutY(280);
            tWelcome.setText("Create an account to start using the application!");
            vBoxUserInfo.setVisible(false);
            buttonLogReg.setLayoutY(400);
            buttonLogReg.setText("Create account");
            buttonLogReg.setOnMouseClicked(event -> goToPage1());
            buttonLogReg.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) goToPage1();});
        }
        tFUsername.setOnKeyTyped(event -> {
            tFUsername.setStyle(null);
            lHintUsernameStart.setVisible(false);
        });
        pFPassword.setOnKeyTyped(event -> {
            pFPassword.setStyle(null);
            tFPassword.setStyle(null);
            lHintPasswordStart.setVisible(false);
        });
        cBoxStayLogged.setOnMouseClicked(event -> {
            if (cBoxStayLogged.isSelected()) {
                App.stayLoggedIn = true;
            } else App.stayLoggedIn = false;
        });
        cBoxStayLogged.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER) ||
                event.getCode().equals(KeyCode.SPACE) ) {
                if (cBoxStayLogged.isSelected()) {
                    App.stayLoggedIn = true;
                } else App.stayLoggedIn = false;
            }
        });
        tForgotPass.focusedProperty().addListener((a, b, c) -> {
            if (c) tForgotPass.setFill(Color.BLUE);
            else tForgotPass.setFill(Color.WHITE);
        });
        tForgotPass.setOnMouseClicked(event -> {

        });
        lHintPasswordStart.setVisible(false);
        lHintUsernameStart.setVisible(false);
    }



    




    private void registerPage1Actions() {
        bViewPassReg.getStyleClass().setAll("button-HidePass");
        bViewPassRepReg.getStyleClass().setAll("button-HidePass");
        
        bViewPassReg.setOnMousePressed(event -> {
            bViewPassReg.getStyleClass().setAll("button-ViewPass");
            bViewPassReg.requestFocus();
            tFPasswordReg.setText(pFPasswordReg.getText());
            pFPasswordReg.setVisible(false);
            tFPasswordReg.setVisible(true);
        });
        bViewPassReg.setOnMouseReleased(event -> {
            bViewPassReg.getStyleClass().setAll("button-HidePass");
            bViewPassReg.focusedProperty().not();
            tFPasswordReg.clear();
            tFPasswordReg.setVisible(false);
            pFPasswordReg.setVisible(true);
        });
        bViewPassRepReg.setOnMousePressed(event -> {
            bViewPassRepReg.getStyleClass().setAll("button-ViewPass");
            bViewPassRepReg.requestFocus();
            tFPasswordRepReg.setText(pFPasswordRepReg.getText());
            pFPasswordRepReg.setVisible(false);
            tFPasswordRepReg.setVisible(true);
        });
        bViewPassRepReg.setOnMouseReleased(event -> {
            bViewPassRepReg.getStyleClass().setAll("button-HidePass");
            bViewPassRepReg.focusedProperty().not();
            tFPasswordRepReg.clear();
            tFPasswordRepReg.setVisible(false);
            pFPasswordRepReg.setVisible(true);
        });
        buttonBackStartPage.getStyleClass().setAll("button-LeftArrow");
        buttonBackStartPage.setOnMouseClicked(event -> {
            App.getStage().setWidth(pLogin.getWidth());
            animatePageTransition(panePage1, pLogin, false);
            startPageActions();
        });
        buttonNextPageReg.getStyleClass().setAll("button-RightArrow");
        buttonNextPageReg.setOnMouseClicked(event -> goToPage2());
        buttonNextPageReg.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) goToPage2();});
        tFEmailReg.setOnKeyTyped(event -> {
            tFEmailReg.setStyle(null);
            lHintEmailReg.setStyle(null);
            lHintEmailReg.setVisible(false);
        });
        pFPasswordReg.setOnKeyTyped(event -> {
            pFPasswordReg.setStyle(null);
            lHintPassReg.setStyle(null);
            lHintPassReg.setVisible(false);
        });
        pFPasswordRepReg.setOnKeyTyped(event -> {
            pFPasswordRepReg.setStyle(null);
            lHintPassRepReg.setStyle(null);
            lHintPassRepReg.setVisible(false);
            if (pFPasswordRepReg.getText().equals(pFPasswordReg.getText()) &&
                !pFPasswordReg.getText().isBlank()) {
                lHintPassRepReg.setText("the password matches");
                lHintPassRepReg.setStyle(labelStyleGood);
                lHintPassRepReg.setVisible(true);
            }
        });
        lHintEmailReg.setVisible(false);
        lHintPassReg.setVisible(false);
        lHintPassRepReg.setVisible(false);
        textRegInfo.setText("Please enter the following informations to register");
    }





    

    private void registerPage2Actions() {
        buttonBackPageReg.getStyleClass().setAll("button-LeftArrow");
        buttonNextPageRecovery.getStyleClass().setAll("button-RightArrow");

        buttonBackPageReg.setOnMouseClicked(event -> {
            animatePageTransition(panePage2, panePage1, false);
        });
        buttonNextPageRecovery.setOnMouseClicked(event -> goToPage3());
        buttonNextPageRecovery.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) goToPage3();});
        tFUsernameReg.setOnKeyTyped(event -> {
            tFUsernameReg.setStyle(null);
            lHintUsernameReg.setStyle(null);
            lHintUsernameReg.setVisible(false);
        });
        cBoxUsername.setOnMouseClicked(event -> checkBoxSelect1());
        cBoxUsername.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER) || 
                event.getCode().equals(KeyCode.SPACE)) {
                checkBoxSelect1();
            }
        });
        lHintUsernameReg.setVisible(false);
        textRegInfo2.setText("You can set an Username to use while logging instead of your email");
    }
    
   






    private void recoveryInfoPageActions() {
        buttonSkipRecovery.getStyleClass().setAll("button-Skip");
        buttonNextPageRecovery2.getStyleClass().setAll("button-RightArrow");

        buttonSkipRecovery.setOnMouseClicked(event -> {createAccount();            goToApp();});
        buttonSkipRecovery.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {createAccount();            goToApp();}});
        buttonNextPageRecovery2.setOnMouseClicked(event -> goToPage4());
        buttonNextPageRecovery2.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) goToPage4();});
        textRegRec1.setText("Please, fill the following fields to help secure your account. \nDon't worry, you can do it latter");
    }







    private void recoveryPage1Actions() {
        buttonNextPageRecovery3.getStyleClass().setAll("button-RightArrow");

        buttonNextPageRecovery3.setOnMouseClicked(event -> goToPage5());
        buttonNextPageRecovery3.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) goToPage5();});
        tFAltEmailRec.setOnKeyTyped(event -> {
            tFAltEmailRec.setStyle(null);
            lHintAltEmailRec.setStyle(null);
            lHintAltEmailRec.setVisible(false);
        });
        tFMainEmailRec.setOnKeyTyped(event -> {
            tFMainEmailRec.setStyle(null);
            lHintMainEmailRec.setStyle(null);
            lHintMainEmailRec.setVisible(false);
        });
        tFPhoneNum.setOnKeyTyped(event -> {
            tFPhoneNum.setStyle(null);
            lHintPhoneNum.setStyle(null);
            lHintPhoneNum.setVisible(false);
        });
        cBoxAltEmail.setOnMouseClicked(event -> checkBoxSelect2());
        cBoxAltEmail.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER) || 
                event.getCode().equals(KeyCode.SPACE)) {
                checkBoxSelect2();
            }
        });
        tFPhoneNum.setOnKeyTyped(event -> {
            if (tFPhoneNum.getText().isEmpty()) lHintPhoneNum.setVisible(true);
            else lHintPhoneNum.setVisible(false);
        });
        tFMainEmailRec.setText(mainEmail);
        lHintAltEmailRec.setVisible(false);
        lHintMainEmailRec.setVisible(false);
        lHintPhoneNum.setVisible(true);
        textRegRec2.setText("Fill theses fields carefully, this will be needed to recovery your account");
    }







    private void recoveryPage2Actions() {
        buttonBackPageRec3.getStyleClass().setAll("button-LeftArrow");
        buttonFinish.getStyleClass().setAll("button-Finish");

        buttonBackPageRec3.setOnMouseClicked(event -> {
            App.getStage().setHeight(600);
            animatePageTransition(panePage5, panePage4, false);
        });
        buttonFinish.setOnMouseClicked(event -> {if (verifyQuestionFields()) finishRegistration();});
        buttonFinish.setOnKeyTyped(event ->     {if (verifyQuestionFields()) finishRegistration();});
        tAAnswer1.setOnMouseClicked(event -> {
            tAAnswer1.setStyle(null);
            tAAnswer1.clear();
            lHintQuestions.setVisible(false);
        });
        tAAnswer2.setOnMouseClicked(event -> {
            tAAnswer2.setStyle(null);
            tAAnswer2.clear();
            lHintQuestions.setVisible(false);
        });
        tAAnswer3.setOnMouseClicked(event -> {
            tAAnswer3.setStyle(null);
            tAAnswer3.clear();
            lHintQuestions.setVisible(false);
        });
        lHintQuestions.setVisible(false);
        textRegRec3.setText("Select a question and give an answer to each one, you will need this when recovering your account");
    }








    private void goToPage1() {
        App.getStage().setWidth(500);
        animatePageTransition(pLogin, panePage1, true);
        registerPage1Actions();
        textRegInfo.requestFocus();
    }

    private void goToPage2() {
        if (verifyRegisterFields()) {
            mainEmail = tFEmailReg.getText();
            password  = pFPasswordReg.getText();
            animatePageTransition(panePage1, panePage2, true);
            registerPage2Actions();
        }
    }

    private void goToPage3() {
        if (tFUsernameReg.getText().isBlank() && !cBoxUsername.isSelected()) {
            lHintUsernameReg.setText("Please, fill the field or select the checkbox");
            animateFieldsError(tFUsernameReg, null, lHintUsernameReg, null);
        } else {
            username = tFUsernameReg.getText();
            animatePageTransition(panePage2, panePage3, true);
            recoveryInfoPageActions();
        }
    }

    private void goToPage4() {
        animatePageTransition(panePage3, panePage4, true);
        recoveryPage1Actions();
    }

    private void goToPage5() {
        if (verifyRecoverFields()) {
            mainEmail = tFMainEmailRec.getText();
            if (cBoxAltEmail.isSelected()) {altEmail = mainEmail;} 
            else altEmail = tFAltEmailRec.getText();
            
            if (!tFPhoneNum.getText().isBlank()) {phoneNum = tFPhoneNum.getText();}
            App.getStage().setHeight(720);
            animatePageTransition(panePage4, panePage5, true);
            recoveryPage2Actions();
        }
    }


    private void createAccount() {
        this.user = new AppUser();
        App.user = user;
        user.setMainEmail(mainEmail);
        user.setPassword(password);
        user.setUsername(username);
        user.setRecoverInfo(false);
    }


    private void finishRegistration() {
        question1 = cBoxQuestion1.getSelectionModel().getSelectedItem();
        question2 = cBoxQuestion2.getSelectionModel().getSelectedItem();
        question3 = cBoxQuestion3.getSelectionModel().getSelectedItem();
        answer1 = tAAnswer1.getText();
        answer2 = tAAnswer2.getText();
        answer3 = tAAnswer3.getText();
        phoneNum = tFPhoneNum.getText();
        createAccount();
        user.setAltEmail(altEmail);
        user.setAnswer1(answer1);
        user.setAnswer2(answer2);
        user.setAnswer3(answer3);
        user.setQuestion1(question1);
        user.setQuestion2(question2);
        user.setQuestion3(question3);
        user.setRecoverInfo(true);
        if (!tFPhoneNum.getText().isBlank()) {
            user.setMobileNumber(phoneNum);
        }
        goToApp();
    }

    private void goToApp() {
        App.setDefAppSize();  
        App.changePage("base");
    }




    private void checkBoxSelect1() {
        if (cBoxUsername.isSelected()) {
            tFUsernameReg.setText(mainEmail);
            username = mainEmail;
            tFUsernameReg.setStyle(null);
            lHintUsernameReg.setVisible(false);
        } else {tFUsernameReg.clear();  username = null;}
    }

    private void checkBoxSelect2() {
        if (cBoxAltEmail.isSelected()) {
            tFAltEmailRec.setText(mainEmail);
            tFAltEmailRec.setEditable(false);
        } else {
            tFAltEmailRec.clear();
            tFAltEmailRec.setEditable(true);
        }
    }





    private boolean verifyStartFields() {
        if (tFUsername.getText().isEmpty()) {
            lHintUsernameStart.setText("Username cannot be empty");
            animateFieldsError(tFUsername, null, lHintUsernameStart, null);
            return false;
        } else if (!tFUsername.getText().equals(user.getUsername())) {
            lHintUsernameStart.setText("Username is wrong");
            animateFieldsError(tFUsername, null, lHintUsernameStart, null);
            return false;
        }

        if (pFPassword.getText().isBlank()) {
            lHintPasswordStart.setText("Password field cannot be empty");
            animateFieldsError(tFPassword, pFPassword, lHintPasswordStart, bViewPass);
            return false;
        } else if (!Password.comparePasswords(pFPassword.getText(), user.getPassword())) {
            lHintPasswordStart.setText("Wrong password");
            animateFieldsError(tFPassword, pFPassword, lHintPasswordStart, bViewPass);
            return false;
        }
        return true;
    }




    private boolean verifyRegisterFields() {
        if (tFEmailReg.getText().isBlank()) {
            lHintEmailReg.setText("Email cannot be empty");
            animateFieldsError(tFEmailReg, null, lHintEmailReg, null);
            return false;
        } else if (!tFEmailReg.getText().matches(genericRegexText+emailRegexGroup)) {
            lHintEmailReg.setText("Email format incorrect");
            animateFieldsError(tFEmailReg, null, lHintEmailReg, null);
            return false;
        } 
        
        if (pFPasswordReg.getText().isBlank()) {
            lHintPassReg.setText("Password cannot be empty");
            animateFieldsError(tFPasswordReg, pFPasswordReg, lHintPassReg, bViewPassReg);
            return false;
        } else if (pFPasswordReg.getText().length() < 6) {
            lHintPassReg.setText("Password too short");
            animateFieldsError(tFPasswordReg, pFPasswordReg, lHintPassReg, bViewPassReg);
            return false;
        }
        
        if (pFPasswordRepReg.getText().isBlank()) {
            lHintPassRepReg.setText("Password cannot be empty");
            animateFieldsError(tFPasswordRepReg, pFPasswordRepReg, lHintPassRepReg, bViewPassRepReg);
            return false;
        } else if (!pFPasswordRepReg.getText().equals(pFPasswordReg.getText())) {
            lHintPassRepReg.setText("Password does not match");
            animateFieldsError(tFPasswordRepReg, pFPasswordRepReg, lHintPassRepReg, bViewPassRepReg);
            return false;
        }
        return true;
    }




    private boolean verifyRecoverFields() {
        if (tFMainEmailRec.getText().isBlank()) {
            lHintMainEmailRec.setText("Email cannot be empty");
            animateFieldsError(tFMainEmailRec, null, lHintMainEmailRec, null);
            return false;
        } else if (!tFMainEmailRec.getText().matches(genericRegexText+emailRegexGroup)){
            lHintMainEmailRec.setText("Email format is not valid");
            animateFieldsError(tFMainEmailRec, null, lHintMainEmailRec, null);
            return false;
        }
        
        if (tFAltEmailRec.getText().isBlank() && !cBoxAltEmail.isSelected()) {
            lHintAltEmailRec.setText("Email cannot be empty");
            animateFieldsError(tFAltEmailRec, null, lHintAltEmailRec, null);
            return false;
        } else if (!tFAltEmailRec.getText().matches(genericRegexText+emailRegexGroup)){
            lHintAltEmailRec.setText("Email format is not valid");
            animateFieldsError(tFAltEmailRec, null, lHintAltEmailRec, null);
            return false;
        }
        return true;
    }




    private boolean verifyQuestionFields() {
        String emptyText = "The field cannot be empty";
        if (tAAnswer1.getText().isBlank()) {
            Shake shake = new Shake(tAAnswer1);
            tAAnswer1.setStyle(tFieldErrorStyle + labelStyleBad);
            tAAnswer1.setText(emptyText);
            lHintQuestions.setText("First answer is empty");
            lHintQuestions.setStyle(labelStyleBad);
            lHintQuestions.setVisible(true);
            shake.play();
            return false;
        }
        if (tAAnswer2.getText().isBlank()) {
            Shake shake = new Shake(tAAnswer2);
            tAAnswer2.setStyle(tFieldErrorStyle + labelStyleBad);
            tAAnswer2.setText(emptyText);
            lHintQuestions.setText("Second answer is empty");
            lHintQuestions.setStyle(labelStyleBad);
            lHintQuestions.setVisible(true);
            shake.play();
            return false;
        }
        if (tAAnswer3.getText().isBlank()) {
            Shake shake = new Shake(tAAnswer3);
            tAAnswer3.setStyle(tFieldErrorStyle + labelStyleBad);
            tAAnswer3.setText(emptyText);
            lHintQuestions.setText("Third answer is empty");
            lHintQuestions.setStyle(labelStyleBad);
            lHintQuestions.setVisible(true);
            shake.play();
            return false;
        }
        return true;
    }





    private void animateFieldsError(TextField tField, PasswordField pField, Label labelHint, Button button) {
        Shake shake = new Shake(tField);
        labelHint.setStyle(labelStyleBad);
        labelHint.setVisible(true);
        if (pField != null) {
            Shake shake2 = new Shake(pField);
            Shake shake3 = new Shake(button);
            pField.setStyle(tFieldErrorStyle);
            shake2.play();
            shake3.play();
        } else {tField.setStyle(tFieldErrorStyle);}
        shake.play();
    }

    private void animatePageTransition(Pane actualPage, Pane nextPage, boolean isFoward) {
        if (isFoward) {
            stackPaneMain.getChildren().add(nextPage);
            SlideAnimation slide = new SlideInRight(nextPage);
            slide.setSpeed(2);
            slide.play();
            stackPaneMain.getChildren().remove(actualPage);
        } else {
            stackPaneMain.getChildren().add(nextPage);
            SlideAnimation slide = new SlideInLeft(nextPage);
            slide.setSpeed(2);
            slide.play();
            stackPaneMain.getChildren().remove(actualPage);
        }
    }






    private void loadNextPages() {
        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("views/register1.fxml"));
            loader1.setController(this);
            Parent root1 = loader1.load();
            panePage1 = (Pane) root1;

            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("views/register2.fxml"));
            loader2.setController(this);
            Parent root2 = loader2.load();
            panePage2 = (Pane) root2;

            FXMLLoader loader3 = new FXMLLoader(App.class.getResource("views/recoveryInfoPres.fxml"));
            loader3.setController(this);
            Parent root3 = loader3.load();
            panePage3 = (Pane) root3;

            FXMLLoader loader4 = new FXMLLoader(App.class.getResource("views/recoveryInfo1.fxml"));
            loader4.setController(this);
            Parent root4 = loader4.load();
            panePage4 = (Pane) root4;
            
            FXMLLoader loader5 = new FXMLLoader(App.class.getResource("views/recoveryInfo2.fxml"));
            loader5.setController(this);
            Parent root5 = loader5.load();
            panePage5 = (Pane) root5;

        } catch (IOException e) {
            System.out.println("Error: An error happened trying to load next pages");
            System.out.println(e.getMessage());                    e.printStackTrace();
        }
    }
}