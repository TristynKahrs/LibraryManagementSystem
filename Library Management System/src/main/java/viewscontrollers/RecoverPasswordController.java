package viewscontrollers;

import controllers.AccountManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RecoverPasswordController {
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