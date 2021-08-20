package viewscontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeNamePaneController implements Initializable {

    public TextField txtUsername;

    public TextField txtPassword;

    public TextField txtNewFullName;

    public Button btnSubmit;
    public void onClickSubmit(ActionEvent event) {
        String strUsername = txtUsername.getText();
        String strPassword = txtPassword.getText();
        String strNewFullName = txtNewFullName.getText();
        if(!strUsername.equals("") && !strPassword.equals("") && !strNewFullName.equals("")) {

        }else{
            lblConfirmation.setText("Must enter all fields");
        }
    }

    public Label lblConfirmation;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
