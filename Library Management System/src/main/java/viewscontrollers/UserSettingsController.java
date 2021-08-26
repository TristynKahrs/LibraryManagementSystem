package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController implements Initializable {
    User activeUser;

    public Pane pneNavigationBar;
    public static ScrollPane updateScrollPane() throws IOException {
        ScrollPane sp = new ScrollPane();
        //TODO  make a pane for navigation
        sp.setContent(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        sp.setPrefSize(132, 311);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        final double SPEED = 0.0075;
        sp.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            sp.setVvalue(sp.getVvalue() - deltaY);
        });
        return sp;
    }

    //onClicks for navigation links change the display pane

    public Pane pneDisplay;

    //pneDisplay controls


    public void onClickChangeName(ActionEvent event) {
        updateActiveUser();
        pneDisplay.getChildren().clear();
        try {
            pneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changename-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }


    public void onClickChangePassword(ActionEvent event) {
        updateActiveUser();
        pneDisplay.getChildren().clear();
        try {
            pneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changepassword-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void logoutOnClick(ActionEvent event) {
        try {
            AccountManagement.activeUser = null;
            ChangeScene.changeScene(event, "login-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void updateActiveUser() {
        activeUser = AccountManagement.activeUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pneNavigationBar.getChildren().add(updateScrollPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
