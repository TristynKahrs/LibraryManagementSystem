package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    private User activeUser;
    public Label lblGreeting;

    public Pane paneDisplay;

    public Button btnBrowse;
    public void onClickBrowse(ActionEvent event) {
        try {
            PagePaneController.setLocation("Profile");
            DisplayBooks.setAllBooks();
            DisplayBooks.resetPageNumber();
            ChangeScene.changeScene(event, "browse-pane.fxml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Button btnFees;
    public void onClickFees(ActionEvent event) {
        PagePaneController.setLocation("Fees");
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName());
        paneDisplay.getChildren().clear();
        try {
            try{
                DisplayBooks.setFeesBookSet(activeUser);
            }catch (FindException ignored) {}
            paneDisplay.getChildren().add(updateScrollPane());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnLostBooks;
    public void onClickLostBooks(ActionEvent event) {
        PagePaneController.setLocation("Lost Book");
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName());
        paneDisplay.getChildren().clear();
        try {
            try {
                DisplayBooks.setLostBooksSet(activeUser);
            }catch (FindException ignored) {}
            paneDisplay.getChildren().add(updateScrollPane());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangeName;
    public void onClickChangeName(ActionEvent event) {
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName());
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(updateScrollPane());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnChangePassword;
    public void onClickChangePassword(ActionEvent event) {
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName());
        paneDisplay.getChildren().clear();
        try {
            paneDisplay.getChildren().add(updateScrollPane());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnCheckedOutBooks;
    public void onClickCheckedOut(ActionEvent event) {
        PagePaneController.setLocation("CheckIn");
        updateActiveUser(event);
        lblGreeting.setText(activeUser.getFullName());
        paneDisplay.getChildren().clear();
        try {
            try {
                DisplayBooks.setCheckedOutSet(activeUser);
            }catch (FindException ignored) {}
            paneDisplay.getChildren().add(updateScrollPane());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Button btnLogout;
    public void logoutOnClick(ActionEvent event) {
        try {
            ChangeScene.changeScene(event, "login-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void updateActiveUser(Event event) {
        activeUser = ChangeScene.receiveData(event);
    }

    public static ScrollPane updateScrollPane() throws IOException{
        ScrollPane sp = new ScrollPane();
        sp.setContent(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        sp.setPrefSize(600, 230);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return sp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO call checkout to start on page
        //TODO change label to have persons name
        lblGreeting.setText("Profile Menu");
        //TODO go to checked out books on default
    }

}
