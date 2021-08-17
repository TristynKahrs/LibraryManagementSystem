package controllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.LoginGUIController;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        try {
            LoginGUIController.launch();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
