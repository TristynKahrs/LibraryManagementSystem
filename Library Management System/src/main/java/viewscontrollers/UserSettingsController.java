package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController implements Initializable {
    User activeUser;

    @FXML
    public Pane pneNavigationBar;
    public ScrollPane updateScrollPane() throws IOException {
        ScrollPane sp = new ScrollPane();
        //TODO  make a pane for navigation
        sp.setContent(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/settingnavigation-pane.fxml").toURI().toURL()));
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

    public Pane pneDisplay;

    public Label lblChangeName;
    public void onClickChangeName(ActionEvent event) {
        updateActiveUser();
        pneDisplay.getChildren().clear();
        try {
            pneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changename-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Label lblChangePassword;
    public void onClickChangePassword(ActionEvent event) {
        updateActiveUser();
        pneDisplay.getChildren().clear();
        try {
            pneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changepassword-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Label lblLogout;
    public void onClickLogout(ActionEvent event) {
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
