package viewscontrollers;

import controllers.AccountManagement;
import controllers.DatabaseOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import models.Book;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserBookController implements Initializable {
    private User activeUser = AccountManagement.activeUser;

    @FXML
    public TabPane tabPane;
    public static TabPane static_tabPane;
    public Tab tabCheckIn;
    public static Tab static_tabCheckIn;
    public AnchorPane pneCheckIn;
    public Label lblCheckInMessage;

    public void onCheckIn() {
        PagePaneController.setLocation("CheckIn");
        updateActiveUser();
        pneCheckIn.getChildren().clear();
        try {
            try {
                DisplayBooks.setCheckedOutSet(activeUser);
                pneCheckIn.getChildren().add(updateScrollPane());
                lblCheckInMessage.setText("");
            } catch (FindException fe) {
                lblCheckInMessage.setText("You have no books checked out");
                pneCheckIn.getChildren().add(lblCheckInMessage);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public Tab tabFees;
    public static Tab static_tabFees;
    public AnchorPane pneFees;
    public Label lblFeesMessage;

    public void onFees() {
        PagePaneController.setLocation("Fees");
        updateActiveUser();
        pneFees.getChildren().clear();
        try {
            try {
                DisplayBooks.setFeesBookSet(activeUser);
                updateFees(activeUser);
                pneFees.getChildren().add(updateScrollPane());
                lblFeesMessage.setText("");
            } catch (FindException fe) {
                lblFeesMessage.setText("You have no books with fees");
                pneFees.getChildren().add(lblFeesMessage);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void updateFees(User user) {
        for(Book book : DisplayBooks.getBookSet()) {
            Date start = DatabaseOperations.getCheckOutDate(book, user);
            Date end = Date.valueOf(LocalDate.now());

            if(start == null) {
                start = DatabaseOperations.getFeeDate(book, user);
            }

            long startMS = start.getTime();
            long endMS = end.getTime();
            long timeDifference = endMS - startMS;
            int dayDifference = (int) (timeDifference/ (1000 * 60 * 60* 24));

            if (dayDifference >= 7) {
                DatabaseOperations.updateFee(book, user, 5.00);
            }
        }
    }

    @FXML
    public Tab tabLost;
    public static Tab static_tabLost;
    public AnchorPane pneLost;
    public Label lblLostMessage;

    public void onLost() {
        PagePaneController.setLocation("Lost Book");
        updateActiveUser();
        pneLost.getChildren().clear();
        try {
            try {
                DisplayBooks.setLostBooksSet(activeUser);
                pneLost.getChildren().add(updateScrollPane());
                lblLostMessage.setText("");
            } catch (FindException fe) {
                lblLostMessage.setText("You have no lost books");
                pneLost.getChildren().add(lblLostMessage);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ScrollPane updateScrollPane() throws IOException {
        ScrollPane sp = new ScrollPane();
        sp.setContent(FXMLLoader.load(new File("src/main/resources/com/example/librarymanagementsystem/page-pane.fxml").toURI().toURL()));
        sp.setPrefSize(600, 300);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        final double SPEED = 0.0075;
        sp.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            sp.setVvalue(sp.getVvalue() - deltaY);
        });
        return sp;
    }

    public void updateActiveUser() {
        activeUser = AccountManagement.activeUser;
    }

    public static void selectTab(TabPane tp, Tab t) {
        tp.getSelectionModel().select(t);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onCheckIn();
        onFees();
        onLost();
        static_tabPane = tabPane;
        static_tabCheckIn = tabCheckIn;
        static_tabFees = tabFees;
        static_tabLost = tabLost;
    }

}
