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

    public static void changeScene(Event event, String fxml) throws IOException {
        fxmlFile = fxml;
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/" + fxmlFile).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

    public static User receiveData(Event event) {
        Node node = (Node)event.getSource();
        Stage newStage = (Stage) node.getScene().getWindow();
        User currentUser = (User) newStage.getUserData();

        return currentUser;
    }
}
