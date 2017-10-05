package com.gustavo.assignment.geocoding.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.assignment.geocoding.model.MapAddress;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by gvaldes
 */

@Component("googleMapsResponseTranslator")
public class GoogleMapsResponseTranslator {

    @Handler
    public void processResponse(Exchange exchange) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        MapAddress mapAddress = jsonMapper.readValue(exchange.getIn().getBody(String.class),
        MapAddress.class);
        exchange.getOut().setBody(mapAddress);
    }
}
