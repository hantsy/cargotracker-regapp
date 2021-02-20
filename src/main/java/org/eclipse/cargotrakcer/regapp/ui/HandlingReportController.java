package org.eclipse.cargotrakcer.regapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.eclipse.cargotrakcer.regapp.client.HandlingReport;
import org.eclipse.cargotrakcer.regapp.client.HandlingReportService;
import org.eclipse.cargotrakcer.regapp.client.HandlingResponse;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.net.ConnectException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@FxmlView("HandlingReport.fxml")
public class HandlingReportController {
    private final static Logger LOGGER = Logger.getLogger(HandlingReportController.class.getName());

    @Inject
    private HandlingReportService handlingReportService;

    @FXML
    private DatePicker completionTimeField;

    @FXML
    private TextField trackingIdField;

    @FXML
    private ComboBox<String> eventTypeField;

    @FXML
    private TextField unLocodeField;

    @FXML
    private TextField voyageNumberField;

    @FXML
    private Text message;

    public HandlingReportController() {
        LOGGER.log(Level.INFO, "calling constructor method.");
    }

    @PostConstruct
    public void init() {
        LOGGER.log(Level.INFO, "calling @PostConstruct method.");
    }

    @FXML
    private void initialize() {
        LOGGER.log(Level.INFO, "calling @FXML initialize method.");
        eventTypeField.getItems().addAll(
                "LOAD",
                "UNLOAD",
                "RECEIVE",
                "CLAIM",
                "CUSTOMS"
        );
    }

    @FXML
    private void onSubmit() {
        var completionTime = completionTimeField.getValue();
        var trackingId = trackingIdField.getText();
        var eventType = eventTypeField.getValue();
        var unLocode = unLocodeField.getText();
        var voyageNumber = voyageNumberField.getText();

        //Jsonb has no option to remove empty values.
        if (null != voyageNumber && "".equals(voyageNumber.trim())) {
            voyageNumber = null;
        }

        var report = HandlingReport.builder()
                .completionTime(completionTime.atTime(LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .eventType(eventType)
                .trackingId(trackingId)
                .unLocode(unLocode)
                .voyageNumber(voyageNumber)
                .build();
        LOGGER.log(Level.INFO, "submitting report: {0}", report);
        // TODO validate report data.
        //
        // CDI injection does not work.
        // see issue: https://github.com/hantsy/cargotracker-regapp/issues/2
        HandlingReportService handlingReportService = CDI.current().select(HandlingReportService.class).get();
        handlingReportService.submitReport(report)
                .thenAccept(handlingResponse -> {

                    if (handlingResponse instanceof HandlingResponse.OK) {
                        message.setText("Submitted successfully!");
                        message.setFill(Color.GREEN);
                    } else {
                        var res = (HandlingResponse.FAILED) handlingResponse;
                        message.setText(res.getMessage());
                        message.setFill(Color.RED);
                    }

                })
                .exceptionally(e -> {
                    LOGGER.log(Level.WARNING, "exception caught: {0}", e.getMessage());
                    if (e instanceof ConnectException) {
                        message.setText("Connection error");
                        message.setFill(Color.RED);
                    }
                    return null;
                })
                .join();
    }
}
