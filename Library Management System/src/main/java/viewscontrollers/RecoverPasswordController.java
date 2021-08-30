package viewscontrollers;

import controllers.AccountManagement;
import controllers.Alerter;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class RecoverPasswordController {
    //TODO make it pretty foo (pw centering when submitted)
    @FXML
    public Button btnBack;
    public void onClickBack(ActionEvent event) {
        try {
            ChangeScene.changeScene(event, "login-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public TextField fullNameSearch;
    public TextField usernameSearch;
    public Label PasswordRetrieval;

    @FXML
    public Button RecoverSubmitBtn;

    public void btnSubmit(ActionEvent event) {
        Window owner= RecoverSubmitBtn.getScene().getWindow();
        String fullName = fullNameSearch.getText();
        String userName = usernameSearch.getText();
        if(!fullName.equals("") && !userName.equals("")) {
            try{
                String password = AccountManagement.recoverPassword(fullName, userName);
                PasswordRetrieval.setText(password);
            }catch (SecurityException | NumberFormatException se) {
                Alerter.showAlert(Alert.AlertType.ERROR, owner, "Forgot Password Failed", "That name and username don't go together, try again.");
            }
        }
    }
}