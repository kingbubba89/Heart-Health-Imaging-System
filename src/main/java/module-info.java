module com.example.hearthealthapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.hearthealthapp to javafx.fxml;
    exports com.example.hearthealthapp;
}