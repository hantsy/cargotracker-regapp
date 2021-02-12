package org.eclipse.cargotrakcer.regapp;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class HandlingReportServiceClient {

    private final static Logger LOGGER = Logger.getLogger(HandlingReportServiceClient.class.getName());

    private final static String DEFAULT_SERVICE_URL = "http://localhost:8080/cargo-tracker/rest/report";

    private final static HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            // .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
            // .authenticator(Authenticator.getDefault())
            .build();

    private String handlingServiceUrl = DEFAULT_SERVICE_URL;

    @PostConstruct
    public void init() {
        String sysHandlingServiceUrl = System.getenv("HANDLING_REPORT_SERVICE_URL");
        
        LOGGER.log(Level.INFO, "env 'HANDLING_REPORT_SERVICE_URL': {0}", sysHandlingServiceUrl);

        if (null != sysHandlingServiceUrl) {
            handlingServiceUrl = sysHandlingServiceUrl;
        }
    }
    
    public void submitReport() {
    }

}
