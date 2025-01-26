module org.example.travelagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens org.example.travelagency to javafx.fxml;
    exports org.example.travelagency;
}