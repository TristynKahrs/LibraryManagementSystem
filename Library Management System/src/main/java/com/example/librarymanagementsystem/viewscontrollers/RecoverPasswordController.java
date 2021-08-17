package com.example.librarymanagementsystem.viewscontrollers;

import com.example.librarymanagementsystem.controllers.AccountManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RecoverPasswordController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
