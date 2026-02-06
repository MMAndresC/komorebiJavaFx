module com.svalero.komorabijavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.svalero.komorabijavafx to javafx.fxml;
    exports com.svalero.komorabijavafx;
}