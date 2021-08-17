package views;

import controllers.AccountManagement;
import javafx.application.Application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginGUIController extends Application implements Initializable {
    Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginGUIController.class.getResource("Main-Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        window.setTitle("Library Management System!");
        window.setScene(scene);
        window.show();
    }

    //Login
    @FXML
    public Pane MainPane;
    public TextField LoginUsername;
    public PasswordField LoginPassword;

    @FXML
    public Button LogInBtn;

    public void onLoginButtonClick(ActionEvent event) {
        String username = LoginUsername.getText();
        String password = LoginPassword.getText();
        if (!username.equals("") && !password.equals("")) {
            try {
                User activeUser = AccountManagement.login(username, password);
//                TODO go to main menu controller/browse-pane
            } catch (SecurityException se) {
                // TODO make a label that says try again when failing login
            }
        }
    }

    @FXML
    public Label CreateAccountLbl;

    public void onCreateAccountLink(MouseEvent event) {
        // TODO go to create account pane
    }

    @FXML
    public Label ForgotPasswordLbl;

    public void onForgotPasswordClick(MouseEvent event) {
        // TODO go to forgot password
    }
    //Login close


    //Create Account
    @FXML
    public AnchorPane CreateAccount;
    public TextField FullName;
    public TextField Username;
    public TextField Password;
    public TextField PasswordConfirmation;

    @FXML
    public Button CreateBtn;

    public void onCreateAccountBtn(ActionEvent event) {
        String fullName = FullName.getText();
        String userName = Username.getText();
        String password = Password.getText();
        String passwordConfirmation = PasswordConfirmation.getText();
        if (!fullName.equals("") && !userName.equals("") && !password.equals("") && !passwordConfirmation.equals("")) {
            if (password.equals(passwordConfirmation)) {
                try {
                    AccountManagement.createAccount(fullName, userName, password);
                    User activeUser = AccountManagement.login(userName, password);
                    //TODO go to main menu controller/browse-pane (login for them)
                } catch (SecurityException se) {
                    //TODO label that says that username is taken
                }
            } else {
                //TODO make a label that says passwords did not match
            }
        }
    }
    //CreateAccount close


    //Recover password
    @FXML
    public TextField usernameSearch;
    public TextField fullNameSearch;
    public Label PasswordRetrieval;

    @FXML
    public Button RecoverSubmitBtn;

    public void btnSubmit(ActionEvent event) {
        String fullName = fullNameSearch.getText();
        String userName = usernameSearch.getText();
        if(!fullName.equals("") && !userName.equals("")) {
            try{
                String password = AccountManagement.recoverPassword(fullName, userName);
                PasswordRetrieval.setText(password);
            }catch (SecurityException se) {
                PasswordRetrieval.setText("That name and username don't go together, try again.");
            }
        }
    }
    //Recover password close

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO Auto-generated method stub
    }
}
