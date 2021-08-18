package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    //TODO all of the little controllers in here
    public Label lblGreeting;
    public ImageView userImg;

    public Pane paneDisplay;

    public Button btnBrowse;

    public void onClickBrowse(ActionEvent event) {
        paneDisplay.getChildren().clear();
        try {
            ChangeScene.changeScene(event, "Browse-pane.fxml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //vbox
    public Button btnFees;

    public void onClickFees(ActionEvent event) {
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/fee-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnLostBooks;

    public void onClickLostBooks(ActionEvent event) {
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/lostbooks-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangeName;

    public void onClickChangeName(ActionEvent event) {
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changename-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangePassword;

    public void onClickChangePassword(ActionEvent event) {
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changepassword-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnCheckedOutBooks;

    public void onClickCheckedOut(ActionEvent event) {
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/checkedout-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    //vbox end

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO change label to have persons name
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/checkedout-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}
