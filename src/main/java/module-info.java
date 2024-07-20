module com.example.sleekflow {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sleekflow to javafx.fxml;
    exports com.example.sleekflow;
}