package viewscontrollers;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NavigationController {

    public Label lblChangeName;
    public Label lblChangePassword;
    public Label lblLogout;
    public Label lblThemes;


    public void onChangeName(MouseEvent mouseEvent) {
        UserSettingsController.onClickChangeName();
    }

    public void onChangePassword(MouseEvent mouseEvent) {
        UserSettingsController.onClickChangePassword();
    }

    public void onLogout(MouseEvent mouseEvent) {
        UserSettingsController.onClickLogout(mouseEvent);
    }

    public void onThemes(MouseEvent mouseEvent) {
        //TODO go to themes pane here
    }
}
