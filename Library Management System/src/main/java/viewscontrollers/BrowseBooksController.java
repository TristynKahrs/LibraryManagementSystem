package viewscontrollers;

import controllers.ChangeScene;
import controllers.DatabaseOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import models.DisplayBooks;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseBooksController implements Initializable {
    //TODO try login constructor
    public BorderPane paneBrowseBooks;

    //Top
    @FXML
    public TextField txtSearch;

    public Button btnSearch;

    public void onSearchClick(ActionEvent event) {
        //TODO get search field, search books, update page pane
        String strSearch = txtSearch.getText();
        if (!strSearch.equals("")) {
            try {
                DisplayBooks.searchBooks(strSearch);
                updateCenterPane();
            } catch (FindException ignored) {}
        }
    }

    public Button btnProfile;

    public void onClickUserProfile(ActionEvent event) {
        try {
<<<<<<< Updated upstream
            ChangeScene.changeScene(event, "User-Profile-pane.fxml");
        } catch(IOException ioe) {
=======
            ChangeScene.changeScene(event, "userprofile-pane.fxml");
        } catch (IOException ioe) {
>>>>>>> Stashed changes
            ioe.printStackTrace();
        }
    }

    //Center
    public Pane panePage;
<<<<<<< Updated upstream
    //TODO THIS IS FING IMPORTANT!!!!!! REMEMBER DIS
    public void updateCenterPane(){
        try {
            panePage.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/Page-pane.fxml").toURI().toURL()));
        }catch (IOException ioe){
=======

    public void updateCenterPane() {
        try {
            panePage.getChildren().clear();
            panePage.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        } catch (IOException ioe) {
>>>>>>> Stashed changes
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
        DisplayBooks.setAllBooks();
        updateCenterPane();
    }
}