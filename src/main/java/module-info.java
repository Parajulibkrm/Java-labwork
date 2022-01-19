module com.bikram.javafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires mysql.connector;
    opens com.bikram.javafinal to javafx.fxml;
    exports com.bikram.javafinal;
    exports com.bikram.javafinal.Models;
    opens com.bikram.javafinal.Models to javafx.fxml;
}