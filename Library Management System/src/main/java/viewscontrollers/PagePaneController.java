package viewscontrollers;

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
    public static Book currentBook;

    public Pane panePage;
    public Pane paneBook_0;
    public Pane paneBook_1;
    public Pane paneBook_2;
    public Pane paneBook_3;

    public Label testLabel;

    public void updateCurrentPage() {
        panePage.getChildren().clear();
        Book[] currentPage = DisplayBooks.page(); //TODO catch the error if there's no books
        Pane[] panes = {paneBook_0, paneBook_1, paneBook_2, paneBook_3};
        for (int i = 0; i < currentPage.length; i++) {
            currentBook = currentPage[i];
            try {
                panes[i].getChildren().add(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        updateCurrentPage();
    }
}