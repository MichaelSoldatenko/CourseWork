module com.example.courselast {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.courselast to javafx.fxml;
    exports com.example.courselast;
}