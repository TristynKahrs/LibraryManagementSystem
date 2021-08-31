package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.User;

import javax.naming.CannotProceedException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeNamePaneController implements Initializable {
    User activeUser;

    public TextField txtUsername;

    public TextField txtPassword;

    public TextField txtNewFullName;

    public Button btnSubmit;

    public void changeName(){
        //TODO wonkyness when trying to double name change
        activeUser = AccountManagement.activeUser;
        String strUsername = txtUsername.getText();
        String strPassword = txtPassword.getText();
        String strNewFullName = txtNewFullName.getText();
        if (!strUsername.equals("") && !strPassword.equals("") && !strNewFullName.equals("")) {
            if(activeUser.getUsername().equals(strUsername) && activeUser.passwordMatches(strPassword)) {
                if(!activeUser.getFullName().equals(strNewFullName)) {
                    AccountManagement.changeFullName(activeUser, strNewFullName);
                    lblConfirmation.setText("Success!");
                    UserProfileController.updateGreeting();
                }else{
                    lblConfirmation.setText("That's already your name");
                }
            }else {
                lblConfirmation.setText("Incorrect login information");
            }
        }
    }

    public void onClickSubmit(ActionEvent event) {
       changeName();
    }

    public Label lblConfirmation;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNewFullName.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                changeName();
            }
        });

    }
}
