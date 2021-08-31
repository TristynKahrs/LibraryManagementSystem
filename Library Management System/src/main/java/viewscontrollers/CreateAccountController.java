package viewscontrollers;

import controllers.AccountManagement;
import controllers.Alerter;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;

import java.io.IOException;
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
    public void onClickBack(ActionEvent event) {
        createAccount(event);
    }

    public void createAccount(Event event){
        try{
            ChangeScene.changeScene(event, "login-pane.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCreateAccountBtn(ActionEvent event) {
        String fullName = txtFullName.getText();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        Window owner= btnCreate.getScene().getWindow();
        String passwordConfirmation = txtPasswordConfirmation.getText();
        if (!fullName.equals("") && !userName.equals("") && !password.equals("") && !passwordConfirmation.equals("")) {
            if (password.equals(passwordConfirmation)) {
                try {
                    AccountManagement.createAccount(fullName, userName, password);
                    User activeUser = AccountManagement.login(userName, password);
                    AccountManagement.setActiveUser(activeUser);
                    ChangeScene.changeScene(event, "browse-pane.fxml");
                } catch (SecurityException se) {
                    Alerter.showAlert(Alert.AlertType.WARNING, owner, "Username Taken", "Username is already taken, please use a different username.");
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            } else {
                Alerter.showAlert(Alert.AlertType.WARNING, owner, "Passwords Don't Match", "Your passwords don't match, please re-enter your passwords.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPasswordConfirmation.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                createAccount(event);
            }
        });

    }

}