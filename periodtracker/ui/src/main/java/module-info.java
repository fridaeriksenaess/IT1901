module periodtracker.ui {
    exports periodtracker.ui;

    requires periodtracker.core;
    requires transitive periodtracker.rest;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens periodtracker.ui to javafx.graphics, javafx.fxml;
}