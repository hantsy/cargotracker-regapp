package org.eclipse.cargotrakcer.regapp.cdi;

import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxWeaver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class FxWeaverProducer {
    private static final Logger LOGGER = Logger.getLogger(FxWeaverProducer.class.getName());

    @Produces
    FxWeaver fxWeaver(CDIControllerFactory callback) {
        var fxWeaver = new FxWeaver((Callback<Class<?>, Object>) callback,
                () -> LOGGER.log(Level.INFO, "calling FxWeaver shutdown hook")
        );

        return fxWeaver;
    }

    public void destroyFxWeaver(@Disposes FxWeaver fxWeaver) {
        LOGGER.log(Level.INFO, "destroying FxWeaver bean...");
        fxWeaver.shutdown();
    }
}
