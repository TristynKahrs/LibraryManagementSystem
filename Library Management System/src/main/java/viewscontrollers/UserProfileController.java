package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    //TODO all of the little controllers in here
    private User activeUser;
    public Label lblGreeting;
    public ImageView userImg;

    public Pane paneDisplay;

    public Button btnBrowse;

    public void onClickBrowse(ActionEvent event) {
        try {
            ChangeScene.changeScene(event, "browse-pane.fxml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //vbox
    public Button btnFees;

    public void onClickFees(ActionEvent event) {
        activeUser = ChangeScene.receiveData(event);
        lblGreeting.setText(activeUser.getFullName() + " here are your fees");
        paneDisplay.getChildren().clear();
        try {
            //set the current bookset to the users fees with books
            //change the controllers to the fee book controllers
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnLostBooks;

    public void onClickLostBooks(ActionEvent event) {
        activeUser = ChangeScene.receiveData(event);
        lblGreeting.setText(activeUser.getFullName() + " here are your lost books");
        paneDisplay.getChildren().clear();
        try {
            //set the current bookset to the users lost books
            //change the controllers to the lost books controllers
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangeName;

    public void onClickChangeName(ActionEvent event) {
        activeUser = ChangeScene.receiveData(event);
        lblGreeting.setText(activeUser.getFullName() + " would you like to change your name?");
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changename-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangePassword;

    public void onClickChangePassword(ActionEvent event) {
        activeUser = ChangeScene.receiveData(event);
        lblGreeting.setText(activeUser.getFullName() + " would you like to change your password?");
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changepassword-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnCheckedOutBooks;

    public void onClickCheckedOut(ActionEvent event) {
        activeUser = ChangeScene.receiveData(event);
        lblGreeting.setText(activeUser.getFullName() + " checked out books!");
        paneDisplay.getChildren().clear();
        try {
            DisplayBooks.setCheckedOutSet(activeUser);
            //change the controllers to the checked out controllers
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    //vbox end

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO call checkout to start on page
        //TODO change label to have persons name
        lblGreeting.setText("Hello " + activeUser.getFullName());
        //TODO go to checked out books on default
    }

}
