package org.eclipse.cargotrakcer.regapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        var loader = new FXMLLoader(new URL("app.fxml"));
        stage.setScene(loader.load());
        stage.setTitle("Incident Logging Application");
        stage.showAndWait();
    }
}
