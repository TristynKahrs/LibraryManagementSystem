module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.naming;
    requires java.sql;


    opens com.example.librarymanagementsystem to javafx.fxml;
    exports com.example.librarymanagementsystem to javafx.graphics;
}