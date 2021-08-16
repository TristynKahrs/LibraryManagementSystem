package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginGUIController implements Initializable {

    //Create Account
    @FXML
    private TextField Fullname;

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private TextField PasswordConfirmation;

    @FXML
    public void onCreateAccountClick(ActionEvent event) {

    }
    //CreateAccount close

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO Auto-generated method stub
    }
}
