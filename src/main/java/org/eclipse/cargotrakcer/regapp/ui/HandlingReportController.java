package org.eclipse.cargotrakcer.regapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import org.eclipse.cargotrakcer.regapp.client.HandlingReport;
import org.eclipse.cargotrakcer.regapp.client.HandlingReportService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;

@ApplicationScoped
@FxmlView("HandlingReport.fxml")
public class HandlingReportController {

    @Inject
    private HandlingReportService handlingReportService;

    @FXML
    private Button hello;

    public HandlingReportController() {
    }

    public void onSubmit(ActionEvent actionEvent) throws Exception {
        var report = new HandlingReport();
        this.handlingReportService.submitReport(report);
    }

    public void onHello(ActionEvent actionEvent) {
        System.out.println("Hello World at @" + Instant.now());
    }
}
