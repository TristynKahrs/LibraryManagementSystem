module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.naming;
    requires java.sql;


    opens views to javafx.fxml;
    exports views to javafx.graphics;
}