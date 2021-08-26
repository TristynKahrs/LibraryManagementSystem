package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    private User activeUser;
    public Label lblGreeting;
    public static Label static_lblGreeting;
    private boolean onSetting = false;

    public ImageView imgUser;

    public void switchMenu(MouseEvent mouseEvent) {
        static_paneDisplay.getChildren().clear();
        if (onSetting) {
            try {
                PagePaneController.setLocation("CheckIn");
                static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/userbookprofile-pane.fxml").toURI().toURL()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            onSetting = false;
        } else {
            try {
                static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/usersettings-pane.fxml").toURI().toURL()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            onSetting = true;
        }
    }


    public Pane paneDisplay;
    public static Pane static_paneDisplay;

    public static void booksCenterPane(String buttonLocation) {
        static_paneDisplay.getChildren().clear();
        try {
            PagePaneController.setLocation(buttonLocation);
            static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/userbookprofile-pane.fxml").toURI().toURL()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void settingsCenterPane() {
        static_paneDisplay.getChildren().clear();
        try {
            static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/usersettings-pane.fxml").toURI().toURL()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Button btnBrowse;

    public void onClickBrowse(ActionEvent event) {
        try {
            PagePaneController.setLocation("CheckIn");
            DisplayBooks.setAllBooks();
            DisplayBooks.resetPageNumber();
            ChangeScene.changeScene(event, "browse-pane.fxml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void updateActiveUser() {
        activeUser = AccountManagement.activeUser;
    }

    public static void updateGreeting() {
        static_lblGreeting.setText(AccountManagement.activeUser.getFullName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_paneDisplay = paneDisplay;
        static_lblGreeting = lblGreeting;
        updateActiveUser();
        try {
            static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/userbookprofile-pane.fxml").toURI().toURL()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateGreeting();
    }

}
