module cargotracker.regapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires weld.se.shaded;
    requires java.logging;
    requires java.net.http;
    requires java.json.bind;
    requires org.eclipse.yasson;
    requires net.rgielen.fxweaver.core;

    exports org.eclipse.cargotrakcer.regapp;
}