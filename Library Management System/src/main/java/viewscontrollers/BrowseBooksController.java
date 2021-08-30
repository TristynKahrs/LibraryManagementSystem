package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseBooksController implements Initializable {
    public BorderPane paneBrowseBooks;

    //Top
    @FXML
    public TextField txtSearch;
    public Button btnSearch;

    public void onSearchClick(ActionEvent event) {
        try {
            String strSearch = txtSearch.getText();
            DisplayBooks.searchBooks(strSearch);
            updateCenterPane();
        } catch (FindException ignored) {
        }
    }


    public void onClickUserProfile(MouseEvent event) {
        try {
            User user = AccountManagement.activeUser;
            ChangeScene.changeScene(event, "userprofile-pane.fxml");
            DisplayBooks.setCheckedOutSet(user);
            UserProfileController.booksCenterPane("CheckIn");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (FindException ignored) {
        }
    }

    //Center
    public Pane centerPane;
    public static Pane static_pane;

    public static void updateCenterPane() {
        static_pane.getChildren().clear();
        try {
            PagePaneController.setLocation("Browse");
            static_pane.getChildren().add(updateScrollPane());
            updatePageNumbers();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Label lblPageNumbers;
    public static Label static_lblPageNumbers;

    public static void updatePageNumbers() {
        static_lblPageNumbers.setText((DisplayBooks.pageNumber + 1) + "/" + DisplayBooks.amountOfPages);
    }

    public static ScrollPane updateScrollPane() throws IOException {
        ScrollPane sp = new ScrollPane();
        sp.setContent(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        sp.setPrefSize(600, 285);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        final double SPEED = 0.0075;
        sp.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            sp.setVvalue(sp.getVvalue() - deltaY);
        });
        return sp;
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
        static_pane = centerPane;
        static_lblPageNumbers = lblPageNumbers;
        updateCenterPane();
    }
}