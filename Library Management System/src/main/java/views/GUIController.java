package views;

import controllers.AccountManagement;
import controllers.ChangeScene;
import controllers.DatabaseConnections;
import controllers.DatabaseOperations;
import javafx.application.Application;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController extends Application {

    @Override
    public void start(Stage window) throws IOException {
        String[] info = new String[4];
        info[0] = "1";
        info[1] = "test";
        info[2] = "tets";
        info[3] = "tst";
        User user = new User(info);
        System.out.println(DatabaseOperations.getAllUsersLostBooks(user));
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/login-pane.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        window.setTitle("Library Management System!");
        window.setScene(new Scene(root, 600, 400));
        window.show();
    }
}
