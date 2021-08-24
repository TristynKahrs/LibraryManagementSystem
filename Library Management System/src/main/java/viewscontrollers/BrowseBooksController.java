package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    public Pane panePage;

    public void updateCenterPane() {
        //TODO FIX THIS YOU FUCKING TWAT
//  panePage.getChildren().clear().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        panePage.getChildren().clear();
        try {
            PagePaneController.setLocation("Check-Out");
            panePage.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
        updateCenterPane();
    }
}