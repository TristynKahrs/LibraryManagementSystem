package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ChangeScene {

    private static Stage stage;
    private static Scene scene;
    private static String fxmlFile;

    public static void changeScene(ActionEvent event, String fxml) throws IOException {
        fxmlFile = fxml;
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/" + fxmlFile).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
