package com.example.librarymanagementsystem.viewscontrollers;

import com.example.librarymanagementsystem.controllers.AccountManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.example.librarymanagementsystem.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
