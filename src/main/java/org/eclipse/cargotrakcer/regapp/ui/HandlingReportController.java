package org.eclipse.cargotrakcer.regapp.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.eclipse.cargotrakcer.regapp.client.HandlingReport;
import org.eclipse.cargotrakcer.regapp.client.HandlingReportService;
import org.eclipse.cargotrakcer.regapp.client.HandlingResponse;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@FxmlView("HandlingReport.fxml")
public class HandlingReportController {
    private final static Logger LOGGER = Logger.getLogger(HandlingReportController.class.getName());

    @Inject
    private HandlingReportService handlingReportService;

    @FXML
    private Button submitButton;

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
    }

    //@PostConstruct
    @FXML
    public void initialize() {
        eventTypeField.setItems(FXCollections.observableList(List.of(
                "LOAD",
                "UNLOAD",
                "RECEIVE",
                "CLAIM",
                "CUSTOMS"
        )));
    }

    public void onSubmit(ActionEvent actionEvent) throws Exception {
        var completionTime = completionTimeField.getValue();
        var trackingId = trackingIdField.getText();
        var eventType = eventTypeField.getValue();
        var unLocode = unLocodeField.getText();
        var voyageNumber = voyageNumberField.getText();

        var report = HandlingReport.builder()
                .completionTime(completionTime.atTime(LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .eventType(eventType)
                .trackingId(trackingId)
                .unLocode(unLocode)
                .voyageNumber(voyageNumber)
                .build();
        LOGGER.log(Level.INFO, "submitting report: {0}", report);
        // TODO validate report data.
        this.handlingReportService.submitReport(report)
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
                .join();
    }

    public void onHello(ActionEvent actionEvent) {
        System.out.println("Hello World at @" + Instant.now());
    }
}
