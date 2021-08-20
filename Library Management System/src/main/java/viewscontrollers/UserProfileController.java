package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    //TODO all of the little controllers in here
    private User activeUser;
    public Label lblGreeting;

    public Pane paneDisplay;

    public Button btnBrowse;
    public void onClickBrowse(ActionEvent event) {
        try {
            DisplayBooks.setAllBooks();
            ChangeScene.changeScene(event, "browse-pane.fxml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //vbox
    public Button btnFees;
    public void onClickFees(ActionEvent event) {
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName() + " here are your fees");
        paneDisplay.getChildren().clear();
        try {
            try{ //TODO ERROR HERE
            DisplayBooks.setFeesBookSet(activeUser);
            }catch (FindException ignored) {}
            //change the controllers to the fee book controllers
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnLostBooks;
    public void onClickLostBooks(ActionEvent event) {
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName() + " here are your lost books");
        paneDisplay.getChildren().clear();
        try { //TODO error here
            try {
                DisplayBooks.setLostBooksSet(activeUser);
            }catch (FindException ignored) {}
            //change the controllers to the lost books controllers
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangeName;
    public void onClickChangeName(ActionEvent event) {
        updateActiveUser(event);
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
        updateActiveUser(event);
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
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName() + " checked out books!");
        paneDisplay.getChildren().clear();
        try { //TODO ERROR HERE
            try {
                DisplayBooks.setCheckedOutSet(activeUser);
            }catch (FindException ignored) {}
            //change the controllers to the checked out controllers
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    //vbox end

    public void updateActiveUser(Event event) {
        activeUser = ChangeScene.receiveData(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO call checkout to start on page
        //TODO change label to have persons name
        //TODO go to checked out books on default
    }

}
