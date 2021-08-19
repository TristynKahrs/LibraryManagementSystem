package viewscontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.Book;
import models.DisplayBooks;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagePaneController implements Initializable {
    Book[] currentPage = null;
    public PagePaneController() {
        currentPage = DisplayBooks.page(); // FIXME: 08/19/21 makes the login really slow
    }

    public static Book currentBook;

    @FXML
    public Pane panePage;
    
    @FXML
    public Pane paneBook_0;

    @FXML
    public Pane paneBook_1;

    @FXML
    public Pane paneBook_2;

    @FXML
    public Pane paneBook_3;

    @FXML
    public Label testLabel;

    public void updateCurrentPage() {
        try {
            panePage.getChildren().clear();
            Pane[] panes = {paneBook_0, paneBook_1, paneBook_2, paneBook_3};
            //TODO catch the error if there's no books
            for (int i = 0; i < currentPage.length; i++) {
                currentBook = currentPage[i];
                panes[i].getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL())); // FIXME: 08/19/21 error right here
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCurrentPage();
    }
}