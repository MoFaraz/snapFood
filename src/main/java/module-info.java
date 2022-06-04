module fourthproject.snapfood {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens fourthproject.snapfood to javafx.fxml;
    exports fourthproject.snapfood;
    exports fourthproject.snapfood.Controller;
    opens fourthproject.snapfood.Controller to javafx.fxml;
}