package viewscontrollers;

import controllers.AccountManagement;
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
        try {
            GUIController.changeTheme();
            AccountManagement.activeUser = null;
            ChangeScene.changeScene(mouseEvent, "login-pane.fxml");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Label lblThemes;

    public void onThemes(MouseEvent mouseEvent) {
        try {
            lblThemes.setText(GUIController.current_theme);
            GUIController.changeTheme();
            ChangeScene.resetScene(mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
