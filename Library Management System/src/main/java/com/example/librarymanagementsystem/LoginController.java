package com.example.librarymanagementsystem;

import controllers.AccountManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
