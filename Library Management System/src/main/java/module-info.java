module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.naming;
    requires java.sql;

    exports com.example.librarymanagementsystem to javafx.graphics;
    opens com.example.librarymanagementsystem to javafx.fxml;
}