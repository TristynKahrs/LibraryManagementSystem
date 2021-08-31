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
import java.util.Objects;

public class GUIController extends Application {
    public static Parent root;
    public static String current_theme;
    public static String default_theme = "/data/DefaultTheme.css";
    public static String dark_theme = "/data/LibraryManagementHub.css";

    @Override
    public void start(Stage window) throws IOException {
        current_theme = default_theme;
        window.setMinWidth(600);
        window.setMinHeight(400);
        window.setResizable(false);
        window.getIcons().add(new Image(GUIController.class.getResourceAsStream("/data/libraryIcon.png")));
        URL url = new File("src/main/resources/com/example/librarymanagementsystem/login-pane.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
        root.getStylesheets().add(getClass().getResource(current_theme).toExternalForm());
        window.setTitle("Library Management System");
        window.setScene(new Scene(root, 600, 400));
        window.show();
    }

    public static void changeTheme() {
        root.getStylesheets().remove(current_theme);
        if(Objects.equals(current_theme, default_theme)) {
            current_theme = dark_theme;
        }else if (Objects.equals(current_theme, dark_theme)) {
            current_theme = default_theme;
        }
        root.getStylesheets().add(GUIController.class.getResource(current_theme).toExternalForm());
    }
}
