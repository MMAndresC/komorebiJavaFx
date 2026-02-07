module com.svalero.komorabijavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.svalero.komorabijavafx.controllers to javafx.fxml;
    opens com.svalero.komorabijavafx.models to com.google.gson;
    exports com.svalero.komorabijavafx;
}