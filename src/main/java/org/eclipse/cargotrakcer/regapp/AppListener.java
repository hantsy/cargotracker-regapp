package org.eclipse.cargotrakcer.regapp;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;

public class AppListener {

    public void onInitialized(@Observes ContainerInitialized event){
        System.out.printf("Starting JavaFX application.");
        App.launch();
    }
}
