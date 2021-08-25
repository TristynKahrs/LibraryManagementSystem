package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    private User activeUser;

    public Label lblGreeting;

    @FXML
    public Button btnLogout;
    public void logoutOnClick(ActionEvent event) {
        try {
            ChangeScene.changeScene(event, "login-pane.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Button btnBrowse;
    public void onClickBrowse(ActionEvent event) {
        try {
            PagePaneController.bookPaneSwitcher("CheckIn");
            DisplayBooks.setAllBooks();
            DisplayBooks.resetPageNumber();
            ChangeScene.changeScene(event, "browse-pane.fxml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Pane paneDisplay;
    public void updatePaneDisplay() {
        try {
            paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/userbookprofile-pane.fxml").toURI().toURL()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //on user settings here

    public void updateActiveUser(Event event) {
        activeUser = ChangeScene.receiveData(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePaneDisplay();
    }

}
