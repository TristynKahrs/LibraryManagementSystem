package viewscontrollers;

<<<<<<< Updated upstream
import javafx.scene.control.TextField;
=======
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
>>>>>>> Stashed changes
import javafx.scene.layout.Pane;

public class PagePaneController {
    public Pane PagePane;

<<<<<<< Updated upstream
=======
public class PagePaneController implements Initializable {
    Book[] currentPage = null;

    public static Book currentBook;

    @FXML
    public Pane panePage;

    @FXML
    public Pane paneBook_0;

    @FXML
>>>>>>> Stashed changes
    public Pane paneBook_1;
    public Pane paneBook_2;
    public Pane paneBook_3;
    public Pane paneBook_4;

<<<<<<< Updated upstream
    public Pane getPagePane() {
        return PagePane;
=======
    public void updateCurrentPage() {
        try {
            panePage.getChildren().clear();
            currentPage = DisplayBooks.page(); //TODO catch the error if there's no books
            Pane[] panes = {paneBook_0, paneBook_1, paneBook_2, paneBook_3};
            for (int i = 0; i < currentPage.length; i++) {
                currentBook = currentPage[i];
                FXMLLoader loader = new FXMLLoader(new File("src/main/resources/com/example/librarymanagementsystem/bookobject-pane.fxml").toURI().toURL());
                loader.setController(new BookObjectPaneController(currentBook));
                panes[i].getChildren().add(loader.load());
                panePage.getChildren().add(panes[i]);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
>>>>>>> Stashed changes
    }

    public void setPagePane(Pane pagePane) {
        PagePane = pagePane;
    }
}