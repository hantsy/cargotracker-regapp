package org.eclipse.cargotrakcer.regapp.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@ExtendWith(WeldJunit5AutoExtension.class)
@EnableAutoWeld
@AddPackages({HandlingReportService.class})
class HandlingReportServiceTest {

    @Inject
    HandlingReportService handlingReportService;

    WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(options().port(8080)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void testHandlingReportServiceExists() {
        assertThat(handlingReportService).isNotNull();
    }


    @Test
    void testOK() throws Exception {
        stubFor(post(urlEqualTo("/cargo-tracker/rest/handling/reports"))
                .willReturn(status(200)));

        assertThat(handlingReportService.submitReport(new HandlingReport()).get().toString()).isEqualTo("OK");
    }

    @Test
    void testFailed() throws Exception {
        stubFor(post(urlEqualTo("/cargo-tracker/rest/handling/reports"))
                .willReturn(aResponse().withStatus(400).withBody("failed")));
        HandlingResponse handlingResponse = handlingReportService.submitReport(new HandlingReport()).get();
        assertThat(handlingResponse).isInstanceOf(HandlingResponse.FAILED.class);

        var failed = (HandlingResponse.FAILED) handlingResponse;
        assertThat(failed.getMessage()).isEqualTo("failed");
    }
}