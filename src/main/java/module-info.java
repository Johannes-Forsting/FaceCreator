module com.example.facecreator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.facecreator to javafx.fxml;
    exports com.example.facecreator;
    exports com.example.javafx;
    opens com.example.javafx to javafx.fxml;
}