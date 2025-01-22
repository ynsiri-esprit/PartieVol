module org.example.travelagency {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.travelagency to javafx.fxml;
    exports org.example.travelagency;
}