package viewscontrollers;

import controllers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import models.Book;
import models.DisplayBooks;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class BookObjectPaneController implements Initializable {
    Book book;
    public BookObjectPaneController(Book book) {
        this.book = book;
    }

    public BookObjectPaneController() {
        book = PagePaneController.currentBook;
    }

    @FXML
    public Pane checkOutPane;

    @FXML
    public Pane checkInPane;

    @FXML
    public Label lblTitle;

    @FXML
    public Label lblAuthor;

    @FXML
    public Button btnCheckOut;

    @FXML
    public Button btnCheckIn;

    @FXML
    public Button btnLost;

    public void onCheckOutClick(ActionEvent event){
        User user = ChangeScene.receiveData(event);
        Window owner= btnCheckOut.getScene().getWindow();
        book = new Book(DatabaseOperations.getBook(lblTitle.getText().substring(7), lblAuthor.getText().substring(8)));

        try {
            LibraryManagement.checkOut(user, book);
            DisplayBooks.setAllBooks();
            checkOutPane.setVisible(false);
        }catch (Exception e){
            Alerter.showAlert(Alert.AlertType.INFORMATION, owner, "Exceeded Limit", "You can't check out more than three books.");
        }
    }

    public void onCheckInClick(ActionEvent event){
        User user = ChangeScene.receiveData(event);
        book = new Book(DatabaseOperations.getBook(lblTitle.getText().substring(7), lblAuthor.getText().substring(8)));
        try {
            LibraryManagement.checkIn(user, book);
            checkInPane.setVisible(false);
        }catch (Exception e){
        }
    }

    public void onReportLostClick(ActionEvent event) {
        User user = ChangeScene.receiveData(event);
        book = new Book(DatabaseOperations.getBook(lblTitle.getText().substring(7), lblAuthor.getText().substring(8)));
        FeeManagement.lostBook(user, book);
        checkInPane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (book != null) {
            lblTitle.setText("Title: " + book.getTitle());
            lblAuthor.setText("Author: " + book.getAuthor());
        }
    }
}
