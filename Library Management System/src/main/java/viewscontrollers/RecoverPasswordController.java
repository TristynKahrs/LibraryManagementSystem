package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RecoverPasswordController {
    //TODO make it pretty foo
    @FXML
    public Button btnBack;
    public void onClickBack(ActionEvent event) {
        try {
            ChangeScene.changeScene(event, "Login-pane.fxml");
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
}