package viewscontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class BrowseBooksController implements Initializable {
    public BorderPane paneBrowseBooks;

    //Top
    @FXML
    public TextField txtSearch;
    public Button btnSearch;

    public void onSearchClick(ActionEvent event) {
        System.out.println("Search Works");
        //TODO get search field, search books, update page pane
    }


    //Center
    public Pane panePage;
    //TODO update pane method?



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
        //TODO set page to be all books at page one (zero)
    }
}