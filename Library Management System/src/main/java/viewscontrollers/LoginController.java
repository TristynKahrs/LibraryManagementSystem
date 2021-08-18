package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Pane paneLogin;
    public TextField txtUsername;
    public PasswordField txtPassword;

    @FXML
    public Button btnLogin;

    public void onClickLogin(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (!username.equals("") && !password.equals("")) {
            try {
                User activeUser = AccountManagement.login(username, password);
                ChangeScene.changeScene(event, "Browse-pane.fxml");
            } catch (SecurityException se) {
                // TODO make a label that says try again when failing login
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    @FXML
    public Label lblCreateAccount;

    public void onCreateAccountLink(MouseEvent event) {
        try {
            ChangeScene.changeScene(event, "Create-Account.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public Label lblForgotPassword;

    public void onForgotPasswordClick(MouseEvent event) {
        try {
            ChangeScene.changeScene(event, "Recover-Password.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}