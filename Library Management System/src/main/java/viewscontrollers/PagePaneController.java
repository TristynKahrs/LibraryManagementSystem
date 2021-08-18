package viewscontrollers;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PagePaneController {
    public Pane PagePane;

    public Pane paneBook_1;
    public Pane paneBook_2;
    public Pane paneBook_3;
    public Pane paneBook_4;

    public Pane getPagePane() {
        return PagePane;
    }

    public void setPagePane(Pane pagePane) {
        PagePane = pagePane;
    }
}