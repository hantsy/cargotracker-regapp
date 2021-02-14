package org.eclipse.cargotrakcer.regapp.cdi;

import javafx.util.Callback;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class CDIControllerFactory implements Callback<Class<?>, Object> {

    @Inject
    private Instance<Object> instance;

    @Override
    public Object call(Class<?> type) {
        Object controller = instance.select(type).get();
        return controller;
    }

}