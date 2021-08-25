package viewscontrollers;

import controllers.ChangeScene;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import models.DisplayBooks;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserBookController implements Initializable {
    private User activeUser;

    @FXML
    public Tab tabCheckIn;
    public AnchorPane paneCheckIn;
    public void onCheckIn(Event event) {
        activeUser = ChangeScene.receiveData(event);
        DisplayBooks.setCheckedOutSet(activeUser);
        PagePaneController.bookPaneSwitcher("CheckIn");
        try {
            paneCheckIn.getChildren().add(updateScrollPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Tab tabFees;
    public AnchorPane paneFees;
    public void onFees(Event event) {
        activeUser = ChangeScene.receiveData(event);
        DisplayBooks.setFeesBookSet(activeUser);
        PagePaneController.bookPaneSwitcher("Fees");
        try {
            paneFees.getChildren().add(updateScrollPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Tab tabLost;
    public AnchorPane paneLost;
    public void onLost(Event event) {
        activeUser = ChangeScene.receiveData(event);
        DisplayBooks.setLostBooksSet(activeUser);
        PagePaneController.bookPaneSwitcher("Lost Book");
        try {
            paneLost.getChildren().add(updateScrollPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScrollPane updateScrollPane() throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
