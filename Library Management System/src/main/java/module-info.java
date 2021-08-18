module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.naming;
    requires java.sql;


    opens views to javafx.fxml;
    exports views to javafx.graphics;
    exports models to javafx.graphics;
    opens models to javafx.fxml;
    exports controllers to javafx.graphics;
    opens controllers to javafx.fxml;
    exports viewscontrollers to javafx.graphics;
    opens viewscontrollers to javafx.fxml;
}