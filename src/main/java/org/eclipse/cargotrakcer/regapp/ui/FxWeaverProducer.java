package org.eclipse.cargotrakcer.regapp.ui;

import net.rgielen.fxweaver.core.FxWeaver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class FxWeaverProducer {
    private static final Logger LOGGER = Logger.getLogger(FxWeaverProducer.class.getName());

    @Produces
    FxWeaver fxWeaver(CDIControllerFactory callback) {
        var fxWeaver = new FxWeaver(callback,
                () -> {
                    LOGGER.log(Level.ALL, "FxWeaver shutdown hook.");
                }
        );

        return fxWeaver;
    }
}
