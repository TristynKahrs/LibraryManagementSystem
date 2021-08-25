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
    public static Pane static_paneDisplay;

    public static void updateCenterPane(String buttonLocation) {
        static_paneDisplay.getChildren().clear();
        try {
            PagePaneController.setLocation(buttonLocation);
//            static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
            static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/userbookprofile-pane.fxml").toURI().toURL()));
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
        final double SPEED = 0.0075;
        sp.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            sp.setVvalue(sp.getVvalue() - deltaY);
        });
        return sp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_paneDisplay = paneDisplay;
        try {
            static_paneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/userbookprofile-pane.fxml").toURI().toURL()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lblGreeting.setText(AccountManagement.activeUser.getFullName());
    }

}
