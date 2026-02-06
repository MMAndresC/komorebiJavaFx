module com.svalero.komorabijavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.svalero.komorabijavafx to javafx.fxml;
    exports com.svalero.komorabijavafx;
}