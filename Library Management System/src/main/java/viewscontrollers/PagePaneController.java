package viewscontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import models.Book;
import models.DisplayBooks;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagePaneController implements Initializable {
    Book[] currentPage = null;
    static FXMLLoader loader;


    public static Book currentBook;

    @FXML
    public Pane panePage;

    @FXML
    public Pane paneBook_0;
    public Pane paneBook_1;
    public Pane paneBook_2;
    public Pane paneBook_3;


    public static void bookPaneSwitcher(String bookObjectType) {
        loader = new FXMLLoader();
        try {
            switch (bookObjectType) {
                case "Browse":
                    loader = new FXMLLoader(new File("src/main/resources/com/example/librarymanagementsystem/bookobject-pane-checkout.fxml").toURI().toURL());
                    break;
                case "CheckIn":
                    loader = new FXMLLoader(new File("src/main/resources/com/example/librarymanagementsystem/bookobject-pane-checkin.fxml").toURI().toURL());
                    break;
                case "Lost Book":
                    loader = new FXMLLoader(new File("src/main/resources/com/example/librarymanagementsystem/bookobject-pane-lost.fxml").toURI().toURL());
                    break;
                case "Fees":
                    loader = new FXMLLoader(new File("src/main/resources/com/example/librarymanagementsystem/bookobject-pane-fee.fxml").toURI().toURL());
                    break;
                default:
                    System.out.println("How did you manage to get here Easter Egg Three!");
                    break;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentPage() {
        //TODO catch the error if there's no books
        currentPage = DisplayBooks.page();
        this.panePage.getChildren().clear();
        Pane[] panes = {paneBook_0, paneBook_1, paneBook_2, paneBook_3};
        for (int i = 0; i < currentPage.length; i++) {
            currentBook = currentPage[i];
             if(currentBook != null){
                try {
                    panes[i].getChildren().add(loader.load());
                    panePage.getChildren().add(panes[i]);
                    loader.setController(new BookObjectPaneController(currentBook));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loader = new FXMLLoader(new File("src/main/resources/com/example/librarymanagementsystem/bookobject-pane-checkout.fxml").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        updateCurrentPage();
    }
}