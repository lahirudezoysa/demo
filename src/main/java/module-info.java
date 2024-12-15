module com.example.javafx_with_jdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.javafx_with_jdbc to javafx.fxml;
    exports com.example.javafx_with_jdbc;
    exports com.example.javafx_with_jdbc.controller;
    opens com.example.javafx_with_jdbc.controller to javafx.fxml;
    exports com.example.javafx_with_jdbc.tm;
    opens com.example.javafx_with_jdbc.tm to javafx.fxml;
}