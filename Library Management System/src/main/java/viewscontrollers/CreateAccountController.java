package viewscontrollers;

import controllers.AccountManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    @FXML
    public Pane paneCreateAccount;
    public TextField txtFullName;
    public TextField txtUsername;
    public TextField txtPassword;
    public TextField txtPasswordConfirmation;

    @FXML
    public Button btnCreate;

    public void onCreateAccountBtn(ActionEvent event) {
        String fullName = txtFullName.getText();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        String passwordConfirmation = txtPasswordConfirmation.getText();
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