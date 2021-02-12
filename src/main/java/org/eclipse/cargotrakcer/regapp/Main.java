package org.eclipse.cargotrakcer.regapp;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
    public static void main(String[] args) {
        Weld weld = new Weld();
        try (WeldContainer container = weld.initialize()) {
            System.out.println("Weld Container is being initialized.");
        }
    }
}
