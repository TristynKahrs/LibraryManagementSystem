package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import models.DisplayBooks;
import models.User;
import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseBooksController implements Initializable {
    //TODO try login constructor
    public BorderPane paneBrowseBooks;
    private User activeUser;

    //Top
    @FXML
    public TextField txtSearch;
    public Button btnSearch;
    public void onSearchClick(ActionEvent event) {
        try {
            String strSearch = txtSearch.getText();
            DisplayBooks.searchBooks(strSearch);
            updateCenterPane();
        }catch(FindException ignored) {}
    }

    @FXML
    public Button btnProfile;

    public void onClickUserProfile(ActionEvent event) {
        try {
            DisplayBooks.setAllBooks();
            ChangeScene.changeSceneWithUser(event, "userprofile-pane.fxml", ChangeScene.receiveData(event));
            PagePaneController.setLocation("Profile");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //Center
    public Pane centerPane;
    public static Pane static_pane;

    public static void updateCenterPane() {
        static_pane.getChildren().clear();
        try {
            PagePaneController.setLocation("Browse");
            static_pane.getChildren().add(updateScrollPane());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ScrollPane updateScrollPane() throws IOException{
        ScrollPane sp = new ScrollPane();
        sp.setContent(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        sp.setPrefSize(600, 285);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return sp;
    }


    //Bottom
    @FXML
    public Button btnNext;

    public void onNextButtonClick(ActionEvent event) {
        DisplayBooks.next();
        updateCenterPane();
    }

    @FXML
    public Button btnPrev;

    public void onPrevButtonClick(ActionEvent event) {
        DisplayBooks.prev();
        updateCenterPane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_pane = centerPane;
        updateCenterPane();
    }
}