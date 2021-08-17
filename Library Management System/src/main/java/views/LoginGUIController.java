package views;

import controllers.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginGUIController extends Application implements Initializable  {
    Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main-Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        window.setTitle("Library Management System!");
        window.setScene(scene);
        window.show();
    }

    //Login
    @FXML
    public AnchorPane MainPane;
    public TextField LoginUsername;
    public TextField LoginPassword;

    @FXML
    public Button LogInBtn;

    public void onLoginButtonClick(ActionEvent event) {
        //check that both textfields are not empty, then try to login
    }

    @FXML
    public Label CreateAccountLbl;
    public void onCreateAccountLink(MouseEvent event) {
        //go to create account pane
    }

    @FXML
    public Label ForgotPasswordLbl;
    public void onForgotPasswordClick(MouseEvent event) {
        // go to forgot password
    }
    //Login close


    //Create Account
    @FXML
    public AnchorPane CreateAccount;
    public TextField FullName;
    public TextField Username;
    public TextField Password;
    public TextField PasswordConfirmation;

    @FXML
    public Button CreateBtn;
    public void onCreateAccountBtn(ActionEvent event) {

    }
    //CreateAccount close


    //Recover password
    @FXML
    public TextField usernameSearch;
    public TextField fullNameSearch;
    public Label PasswordRetrieval;

    @FXML
    public void btnSubmit(ActionEvent event) {

    }
    //Recover password close


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO Auto-generated method stub
    }

}
