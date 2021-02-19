package org.eclipse.cargotrakcer.regapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.eclipse.cargotrakcer.regapp.cdi.CDIControllerFactory;
import org.eclipse.cargotrakcer.regapp.cdi.StageReadyEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PrimaryStageInitializer {
    private final static Logger LOGGER = Logger.getLogger(PrimaryStageInitializer.class.getName());

    @Inject
    FxWeaver fxWeaver;

//    @Inject
//    CDIControllerFactory controllerFactory;

    public void onInitialized(@Observes @StageReadyEvent Stage stage) throws IOException {
        LOGGER.log(Level.INFO, "Observes StageReadyEvent: {0}", stage);
        stage.setTitle("Incident Logging Application");
        Parent root = fxWeaver.loadView(HandlingReportController.class);
//        var loader = new FXMLLoader(HandlingReportController.class.getResource("HandlingReport.fxml"));
//        loader.setControllerFactory(controllerFactory);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
