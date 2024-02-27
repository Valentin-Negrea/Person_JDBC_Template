module com.example.seminar322 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.seminar322 to javafx.fxml;
    exports com.example.seminar322;
    exports com.example.seminar322.Domain;
    opens com.example.seminar322.Domain to javafx.fxml;
    exports com.example.seminar322.Repository;
    opens com.example.seminar322.Repository to javafx.fxml;
}