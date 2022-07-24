package viewscontrollers;

import controllers.ChangeScene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import views.GUIController;

import java.io.IOException;

public class NavigationController {

    public Label lblChangeName;
    public Label lblChangePassword;
    public Label lblLogout;

    public void onChangeName(MouseEvent mouseEvent) {
        UserSettingsController.onClickChangeName();
    }

    public void onChangePassword(MouseEvent mouseEvent) {
        UserSettingsController.onClickChangePassword();
    }

    public void onLogout(MouseEvent mouseEvent) {
        UserSettingsController.onClickLogout(mouseEvent);
    }

    public Label lblThemes;

    public void onThemes(MouseEvent mouseEvent) {
        try {
            GUIController.changeTheme();
            ChangeScene.resetScene(mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
