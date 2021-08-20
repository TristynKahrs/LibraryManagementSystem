package controllers;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Alerter {

    /**
     * This method will create a new alert if something requires it to go.
     * @param alertType Alert.AlertType allows you to set the type (i.e. ERROR, INFO, MESSAGE, etc.)
     * @param owner Window owner will put the alert over the current window.
     * @param title String title sets the top bar with the title you want
     * @param message String message sets the message you want to display to the user.
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
