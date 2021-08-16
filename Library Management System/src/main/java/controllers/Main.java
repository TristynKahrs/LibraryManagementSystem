package controllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.CLI;
import java.io.IOException;

public class Main  extends Application {

    public static void main(String[] args){
        System.out.println("Hello Chris_1");
        try {
//            CLI.startupMenu();
            launch();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Hello Chris_2");
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main-Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
