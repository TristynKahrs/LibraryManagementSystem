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

import java.io.File;
import java.io.IOException;
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
        System.out.println("Search Works");
        //TODO get search field, search books, update page pane
    }

    public Button btnProfile;
    public void onClickUserProfile(ActionEvent event) {
        try {
            ChangeScene.changeScene(event, "userprofile-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //Center
    public Pane panePage;
    public void updateCenterPane(){
        try {
            panePage.getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }



    //Bottom
    @FXML
    public Button btnNext;
    public void onNextButtonClick(ActionEvent event) {
        //TODO go next, update page pane
    }

    @FXML
    public Button btnPrev;
    public void onPrevButtonClick(ActionEvent event) {
        //TODO go prev, update page pane
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCenterPane();
        //TODO set page to be all books at page one (zero)
    }
}