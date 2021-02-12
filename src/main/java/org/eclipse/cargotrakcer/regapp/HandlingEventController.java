package org.eclipse.cargotrakcer.regapp;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HandlingEventController {
    private final HandlingReportServiceClient handlingReportServiceClient;

    @Inject
    public HandlingEventController(HandlingReportServiceClient handlingReportServiceClient) {
        this.handlingReportServiceClient = handlingReportServiceClient;
    }

    
    public void onSubmit() {
        this.handlingReportServiceClient.submitReport();
    }

}
