package viewscontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookObjectPaneController implements Initializable {
    @FXML
    public Label lblTitle;
    public Label lblAuthor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Book book = PagePaneController.currentBook;
            lblTitle.setText("Title: " + book.getTitle());
            lblAuthor.setText("Author: " + book.getAuthor());
        } catch (Exception ignored) {} //TODO MAYBE HANDLE IN THE BOOKCONTROL THING?
    }
}
