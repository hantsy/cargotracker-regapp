package org.eclipse.cargotrakcer.regapp.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.eclipse.cargotrakcer.regapp.cdi.StageReadyEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PrimaryStageInitializer {
    private final static Logger LOGGER = Logger.getLogger(PrimaryStageInitializer.class.getName());

    @Inject
    FxWeaver fxWeaver;

    public void onInitialized(@Observes @StageReadyEvent Stage stage){
         LOGGER.log(Level.INFO, "Observes StageReadyEvent: {0}", stage);
         stage.setTitle("Event Logger");
         stage.setScene( new Scene(fxWeaver.loadView(HandlingReportController.class), 800, 600));
         stage.show();
    }
}
