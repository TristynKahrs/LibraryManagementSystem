package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.User;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController implements Initializable {
    static User activeUser;

    @FXML
    public Pane pneNavigationBar;
    public ScrollPane updateScrollPane() throws IOException {
        ScrollPane sp = new ScrollPane();
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
    public static Pane static_pneDisplay;


    public static void onClickChangeName() {
        updateActiveUser();
        static_pneDisplay.getChildren().clear();
        try {
            static_pneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changename-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void onClickChangePassword() {
        updateActiveUser();
        static_pneDisplay.getChildren().clear();
        try {
            static_pneDisplay.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/changepassword-pane.fxml").toURI().toURL()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void onClickLogout(MouseEvent event) {
        try {
            AccountManagement.activeUser = null;
            ChangeScene.changeScene(event, "login-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void updateActiveUser() {
        activeUser = AccountManagement.activeUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_pneDisplay = pneDisplay;
        onClickChangeName(); //TODO CHANGE THIS TO THEMES
        try {
            pneNavigationBar.getChildren().add(updateScrollPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
