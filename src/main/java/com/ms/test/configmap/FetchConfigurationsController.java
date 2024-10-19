package com.ms.test.configmap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FetchConfigurationsController {

    @Value("${apigee.mttr.ttl}")
    private String apigeeMttrTtl; // Directly read the property
    @Value("${apigee.whatsapp.search-url}")
    private String fastFaxSearchUrl; // Read from external file
    @Value("${env.message:Default environment message}")
    private String envMessage; // Read from Config Map as Environment Variable
    @Value("${app.property1:Default Property1}")
    private String prop1; // Read from Config Map as Environment Variable
    @Value("${app.property2:Default property2}")
    private String prop2; // Read from Config Map as Environment Variable

    @GetMapping("/app-config")
    public String getApplicationConfiguration() {
        StringBuffer sb= new StringBuffer("Reading config property from: <ol>" ).append("<br>");
        sb.append("<li>Local application.yml: " + apigeeMttrTtl).append("</li><br>")
                .append("<li>Property from External file external-config/ext-config.yml: " + fastFaxSearchUrl).append("</li><br>")
                .append("<li>Property1 from Configmap volume external-config/ext-config.yml: " + prop1).append("</li><br>")
                .append("<li>Property2 from Configmap volume external-config/ext-config.yml: " + prop2).append("</li><br>")
                .append("<li>Property from Configmap exposed as environment variable: " + envMessage).append("</li></ol>");
        return sb.toString();
    }
}
