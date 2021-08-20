package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ChangeScene {

    private static Stage stage;
    private static Scene scene;
    private static String fxmlFile;

    /**
     * This changes the scene from one pane to another.
     * @param event Event event passes in the event from a clickable JavaFX controls
     * @param fxml String fxml requires the name of the .fxml file you wish to change to.
     * @throws IOException
     */
    public static void changeScene(Event event, String fxml) throws IOException {
        fxmlFile = fxml;
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/" + fxmlFile).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method helps us set the user data, so we can receive it in other panes.
     * @param event Event event passes in the event from a clickable JavaFX controls
     * @param fxml String fxml requires the name of the .fxml file you wish to change to.
     * @param user A user Object used, so we can set the data for later pane.
     * @throws IOException
     */
    public static void changeSceneWithUser(Event event, String fxml, User user) throws IOException {
        User currentUser = user;
        Node node = (Node)event.getSource();
        Stage oldStage = (Stage)node.getScene().getWindow();

        fxmlFile = fxml;
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/" + fxmlFile).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        oldStage.setUserData(currentUser);
        scene = new Scene(root);
        oldStage.setScene(scene);
        oldStage.show();
    }

    /**
     * This method is used to receive set data from a previous pane, so we can get the user object.
     * @param event Event event passes in the event from a clickable JavaFX controls
     * @return Returns a User Object from retrieved data from previous pane.
     */
    public static User receiveData(Event event) {
        Node node = (Node)event.getSource();
        Stage newStage = (Stage) node.getScene().getWindow();
        User currentUser = (User) newStage.getUserData();

        return currentUser;
    }
}
