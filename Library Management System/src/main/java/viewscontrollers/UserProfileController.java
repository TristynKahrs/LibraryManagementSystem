package viewscontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    //TODO CREATE fee-pane.fxml, lostbooks-pane.fxml, changename-pane.fxml, changepassword-pane.fxml, checkedout-pane.fxml
    public Label lblGreeting;
    public ImageView userImg;

    public Pane paneDisplay; //TODO buttons update this pane

    //vbox
    public Button btnFees;

    public void onClickFees(ActionEvent event) {
        //switch paneDisplay to fee pane
    }

    public Button btnLostBooks;
    public void onClickLostBooks(ActionEvent event) {
        //switch paneDisplay to lost books pane
    }

    public Button btnChangeName;
    public void onClickChangeName(ActionEvent event) {
        //switch paneDisplay to change name pane
    }

    public Button btnChangePassword;
    public void onClickChangePassword(ActionEvent event) {
        //switch paneDisplay to change password pane
    }

    public Button btnCheckedOutBooks;
    public void onClickCheckedOut(ActionEvent event) {
        //switch paneDisplay to checked out pane
    }
    //vbox end

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO set paneDisplay to checked out pane
    }
}
