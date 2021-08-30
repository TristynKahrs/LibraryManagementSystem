package views;

import javafx.application.Application;
import java.io.File;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.net.URL;

public class GUIController extends Application {
    public static String css = "/data/DefaultTheme.css";

    @Override
    public void start(Stage window) throws IOException {
        window.setMinWidth(600);
        window.setMinHeight(400);
        window.setResizable(false);
        window.getIcons().add(new Image(GUIController.class.getResourceAsStream("/data/libraryIcon.png")));
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/login-pane.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        root.getStylesheets().add(getClass().getResource(css).toExternalForm());
        window.setTitle("Library Management System");
        window.setScene(new Scene(root, 600, 400));
        window.show();
    }
}
