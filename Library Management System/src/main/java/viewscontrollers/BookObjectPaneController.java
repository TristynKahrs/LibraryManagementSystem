package viewscontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookObjectPaneController implements Initializable {
    Book book = null;
    public BookObjectPaneController(Book book) {
        this.book = book;
    }

    public BookObjectPaneController() {
        book = PagePaneController.currentBook;
    }

    @FXML
    public Label lblTitle;

    @FXML
    public Label lblAuthor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (book != null) {
            lblTitle.setText("Title: " + book.getTitle());
            lblAuthor.setText("Author: " + book.getAuthor());
        }
    }
}
