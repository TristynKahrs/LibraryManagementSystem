package viewscontrollers;

import controllers.AccountManagement;
import controllers.ChangeScene;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserBookController implements Initializable {
    private User activeUser = AccountManagement.activeUser;

    @FXML
    public Tab tabCheckIn;
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
    public AnchorPane pneFees;
    public Label lblFeesMessage;

    public void onFees() {
        PagePaneController.setLocation("Fees");
        updateActiveUser();
        pneFees.getChildren().clear();
        try {
            try {
                DisplayBooks.setFeesBookSet(activeUser);
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

    @FXML
    public Tab tabLost;
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
        sp.setPrefSize(600, 230);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onCheckIn();
        onFees();
        onLost();
    }

}
