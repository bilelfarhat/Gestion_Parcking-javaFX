module NewProjet {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    opens com.example.newprojet to javafx.fxml;
    exports com.example.newprojet;
}