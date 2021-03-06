package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import models.User;

import javax.naming.CannotProceedException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordPaneController implements Initializable {
    User activeUser;

    public TextField txtCurrentPassword;

    public TextField txtNewPassword;

    public TextField txtConfirmPassword;

    public Button btnSubmit;

    public void changePassword(){
        activeUser = AccountManagement.activeUser;
        String strCurrentPassword = txtCurrentPassword.getText();
        String strNewPassword = txtNewPassword.getText();
        String strConfirmPassword = txtConfirmPassword.getText();
        if (!strCurrentPassword.equals("") && !strNewPassword.equals("") && !strConfirmPassword.equals("")) {
            if (strNewPassword.equals(strConfirmPassword)) {
                try {
                    AccountManagement.changePassword(activeUser, strCurrentPassword, strNewPassword);
                    lblConfirmation.setText("Success!");
                }catch (SecurityException se) {
                    lblConfirmation.setText("You must enter your old password correctly");
                }catch (CannotProceedException cpe) {
                    lblConfirmation.setText("Your new password cannot match your old password");
                }
            }else{
                lblConfirmation.setText("Your password confirmation was incorrect");
            }
        } else {
            lblConfirmation.setText("Must enter all fields");
        }
    }

    public void onClickSubmit(ActionEvent event) {
       changePassword();
    }

    public Label lblConfirmation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtConfirmPassword.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                changePassword();
            }
        });
    }

}
