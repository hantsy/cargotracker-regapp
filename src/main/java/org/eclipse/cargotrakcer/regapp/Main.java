package org.eclipse.cargotrakcer.regapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.eclipse.cargotrakcer.regapp.ui.PrimaryStageInitializer;
import org.eclipse.cargotrakcer.regapp.ui.StageReadyEvent;
import org.jboss.weld.bootstrap.spi.BeanDiscoveryMode;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    static WeldContainer container;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        container.shutdown();
        Platform.exit();
    }

    @Override
    public void init() throws Exception {
        Weld weld = new Weld();
//                .setBeanDiscoveryMode(BeanDiscoveryMode.ANNOTATED)
//                .addPackage(true, Main.class);
        container = weld.initialize();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.log(Level.INFO, "fires StageReadyEvent.");
        // start primary stage.
        container.getBeanManager().fireEvent(stage, StageReadyEvent.Literal);
    }
}
