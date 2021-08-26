package viewscontrollers;

import controllers.AccountManagement;
import controllers.Alerter;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import models.DisplayBooks;
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
        Window owner= btnLogin.getScene().getWindow();
        if (!username.equals("") && !password.equals("")) {
            try {
                User activeUser = AccountManagement.login(username, password);
                AccountManagement.setActiveUser(activeUser);
                ChangeScene.changeScene(event, "browse-pane.fxml");
            } catch (SecurityException | NumberFormatException se) {
                Alerter.showAlert(Alert.AlertType.ERROR, owner, "Failed Login", "Username and or Password is incorrect.");
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    @FXML
    public Label lblCreateAccount;

    public void onCreateAccountLink(MouseEvent event) {
        try {
            ChangeScene.changeScene(event, "createaccount-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public Label lblForgotPassword;

    public void onForgotPasswordClick(MouseEvent event) {
        try {
            ChangeScene.changeScene(event, "recoverpassword-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO remove this in final presentation
        txtUsername.setText("TestUser");
        txtPassword.setText("TestPW");
    }
}