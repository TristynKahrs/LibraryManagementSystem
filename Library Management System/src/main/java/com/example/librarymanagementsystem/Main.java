package com.example.librarymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args){
//        DatabaseOperations.createUser("Test User", "TestUser", "TestPW");
        launch();
    }

    @Override
    public void start(Stage window) throws IOException {
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/Main-Menu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        window.setTitle("Library Management System!");
        window.setScene(new Scene(root, 600, 400));
        window.show();
    }

}
