package viewscontrollers;

import controllers.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Book;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.format;

public class BookObjectPaneController implements Initializable {
    Book book;
    public BookObjectPaneController(Book book) {
        this.book = book;
    }

    public BookObjectPaneController() {
        book = PagePaneController.currentBook;
    }

    @FXML public Pane checkOutPane;
    @FXML public Pane checkInPane;
    @FXML public Pane lostPane;
    @FXML public Label lblTitle;
    @FXML public Label lblAuthor;
    @FXML public ImageView imgBook;
    @FXML public Button btnCheckOut;
    @FXML public Button btnCheckIn;
    @FXML public Button btnLost;
    @FXML public Button btnFound;
    @FXML public Button btnPay;
    @FXML public Label lblFeeAmount;
    @FXML public TextField txtPayAmount;


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
        LibraryManagement.checkIn(user, book);
        checkInPane.setVisible(false);
    }

    public void onReportFoundClick(ActionEvent event) {
        User user = ChangeScene.receiveData(event);
        book = new Book(DatabaseOperations.getBook(lblTitle.getText().substring(7), lblAuthor.getText().substring(8)));
        FeeManagement.foundBook(user, book);
        LibraryManagement.checkIn(user, book);
        lostPane.setVisible(false);
    }

    public void onPayClick(ActionEvent event) {
        ChangeScene.createPopUp(event, "fee-popup-pane.fxml");
    }

    public void onSubmitClick(ActionEvent actionEvent) {
        Window owner = ((Node)actionEvent.getSource()).getScene().getWindow();
        try {
            Double.parseDouble(txtPayAmount.getText());
            lblFeeAmount.setText("$" + txtPayAmount.getText());
        } catch(NumberFormatException nfe) {
            Alerter.showAlert(Alert.AlertType.ERROR, owner, "Invalid Payment", "Please Enter a Dollar Amount!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (book != null) {
            lblTitle.setText("Title: " + book.getTitle());
            lblAuthor.setText("Author: " + book.getAuthor());
            File bookFile = new File("src//main//resources//images//" + book.getPrimaryKey() + ".jpg");
            Image bookIMG = new Image(bookFile.toURI().toString());
            imgBook.setImage(bookIMG);
        }
    }
}
